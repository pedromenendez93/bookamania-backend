package com.bookamania.universe.domain.model;

import com.bookamania.universe.domain.exception.ShowNotFoundException;
import com.bookamania.universe.domain.model.valueobject.MatchId;
import com.bookamania.universe.domain.model.valueobject.ShowId;
import com.bookamania.universe.domain.model.valueobject.SuperstarId;
import com.bookamania.universe.domain.model.valueobject.UniverseId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UniverseTest {

    private Universe universe;
    private UniverseId universeId;

    @BeforeEach
    void setUp() {
        universeId = UniverseId.create();
        universe = new Universe(universeId, "WWE");
    }

    @Test
    @DisplayName("Should create a universe with id and name")
    void shouldCreateUniverseWithIdAndName() {
        // Given
        UniverseId id = UniverseId.create();
        String name = "AEW";

        // When
        Universe universe = new Universe(id, name);

        // Then
        assertNotNull(universe);
    }

    @Test
    @DisplayName("Should schedule a show and return show id")
    void shouldScheduleShowAndReturnShowId() {
        // Given
        LocalDate date = LocalDate.of(2026, 2, 15);

        // When
        ShowId showId = universe.scheduleShow(date);

        // Then
        assertNotNull(showId);
    }

    @Test
    @DisplayName("Should schedule multiple shows")
    void shouldScheduleMultipleShows() {
        // Given
        LocalDate date1 = LocalDate.of(2026, 2, 15);
        LocalDate date2 = LocalDate.of(2026, 2, 22);

        // When
        ShowId showId1 = universe.scheduleShow(date1);
        ShowId showId2 = universe.scheduleShow(date2);

        // Then
        assertNotNull(showId1);
        assertNotNull(showId2);
        assertNotEquals(showId1, showId2);
    }

    @Test
    @DisplayName("Should schedule a match in a show")
    void shouldScheduleMatchInShow() {
        // Given
        LocalDate date = LocalDate.of(2026, 2, 15);
        ShowId showId = universe.scheduleShow(date);
        List<SuperstarId> participants = List.of(
                SuperstarId.create(),
                SuperstarId.create()
        );

        // When
        MatchId matchId = universe.scheduleMatch(showId, 1, participants);

        // Then
        assertNotNull(matchId);
    }

    @Test
    @DisplayName("Should schedule multiple matches in the same show")
    void shouldScheduleMultipleMatchesInSameShow() {
        // Given
        LocalDate date = LocalDate.of(2026, 2, 15);
        ShowId showId = universe.scheduleShow(date);
        List<SuperstarId> participants1 = List.of(SuperstarId.create(), SuperstarId.create());
        List<SuperstarId> participants2 = List.of(SuperstarId.create(), SuperstarId.create());

        // When
        MatchId matchId1 = universe.scheduleMatch(showId, 1, participants1);
        MatchId matchId2 = universe.scheduleMatch(showId, 2, participants2);

        // Then
        assertNotNull(matchId1);
        assertNotNull(matchId2);
        assertNotEquals(matchId1, matchId2);
    }

    @Test
    @DisplayName("Should throw ShowNotFoundException when scheduling match in non-existent show")
    void shouldThrowShowNotFoundExceptionWhenSchedulingMatchInNonExistentShow() {
        // Given
        ShowId nonExistentShowId = ShowId.create();
        List<SuperstarId> participants = List.of(SuperstarId.create(), SuperstarId.create());

        // When & Then
        ShowNotFoundException exception = assertThrows(
                ShowNotFoundException.class,
                () -> universe.scheduleMatch(nonExistentShowId, 1, participants)
        );

        assertEquals("Show not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should register match result successfully")
    void shouldRegisterMatchResultSuccessfully() {
        // Given
        LocalDate date = LocalDate.of(2026, 2, 15);
        ShowId showId = universe.scheduleShow(date);
        SuperstarId superstar1 = SuperstarId.create();
        SuperstarId superstar2 = SuperstarId.create();
        List<SuperstarId> participants = List.of(superstar1, superstar2);
        MatchId matchId = universe.scheduleMatch(showId, 1, participants);
        MatchResult result = new MatchResult(superstar1);

        // When & Then
        assertDoesNotThrow(() -> universe.registerMatchResult(showId, matchId, result));
    }

    @Test
    @DisplayName("Should throw ShowNotFoundException when registering result in non-existent show")
    void shouldThrowShowNotFoundExceptionWhenRegisteringResultInNonExistentShow() {
        // Given
        ShowId nonExistentShowId = ShowId.create();
        MatchId matchId = MatchId.create();
        SuperstarId winner = SuperstarId.create();
        MatchResult result = new MatchResult(winner);

        // When & Then
        ShowNotFoundException exception = assertThrows(
                ShowNotFoundException.class,
                () -> universe.registerMatchResult(nonExistentShowId, matchId, result)
        );

        assertEquals("Show not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should schedule match and register result in complete flow")
    void shouldScheduleMatchAndRegisterResultInCompleteFlow() {
        // Given
        LocalDate date = LocalDate.of(2026, 2, 15);
        ShowId showId = universe.scheduleShow(date);
        SuperstarId superstar1 = SuperstarId.create();
        SuperstarId superstar2 = SuperstarId.create();
        List<SuperstarId> participants = List.of(superstar1, superstar2);

        // When
        MatchId matchId = universe.scheduleMatch(showId, 1, participants);
        MatchResult result = new MatchResult(superstar1);

        // Then
        assertNotNull(matchId);
        assertDoesNotThrow(() -> universe.registerMatchResult(showId, matchId, result));
    }
}