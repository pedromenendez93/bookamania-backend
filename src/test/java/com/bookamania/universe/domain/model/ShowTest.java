package com.bookamania.universe.domain.model;

import com.bookamania.universe.domain.exception.MatchNotFoundException;
import com.bookamania.universe.domain.model.valueobject.MatchId;
import com.bookamania.universe.domain.model.valueobject.ShowId;
import com.bookamania.universe.domain.model.valueobject.SuperstarId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShowTest {

    private Show show;
    private LocalDate showDate;

    @BeforeEach
    void setUp() {
        showDate = LocalDate.of(2026, 2, 15);
        show = Show.schedule(showDate);
    }

    @Test
    @DisplayName("Should create a show with date")
    void shouldCreateShowWithDate() {
        // Given & When
        Show createdShow = Show.schedule(showDate);

        // Then
        assertNotNull(createdShow);
        assertNotNull(createdShow.getId());
        assertEquals(showDate, createdShow.getDate());
    }

    @Test
    @DisplayName("Should generate unique ShowId for each show")
    void shouldGenerateUniqueShowIdForEachShow() {
        // Given & When
        Show show1 = Show.schedule(showDate);
        Show show2 = Show.schedule(showDate);

        // Then
        assertNotNull(show1.getId());
        assertNotNull(show2.getId());
        assertNotEquals(show1.getId(), show2.getId());
    }

    @Test
    @DisplayName("Should schedule a match in the show")
    void shouldScheduleMatchInShow() {
        // Given
        int order = 1;
        List<SuperstarId> participants = List.of(
                SuperstarId.create(),
                SuperstarId.create()
        );

        // When
        MatchId matchId = show.scheduleMatch(order, participants);

        // Then
        assertNotNull(matchId);
    }

    @Test
    @DisplayName("Should schedule multiple matches in the same show")
    void shouldScheduleMultipleMatchesInSameShow() {
        // Given
        List<SuperstarId> participants1 = List.of(SuperstarId.create(), SuperstarId.create());
        List<SuperstarId> participants2 = List.of(SuperstarId.create(), SuperstarId.create());

        // When
        MatchId matchId1 = show.scheduleMatch(1, participants1);
        MatchId matchId2 = show.scheduleMatch(2, participants2);

        // Then
        assertNotNull(matchId1);
        assertNotNull(matchId2);
        assertNotEquals(matchId1, matchId2);
    }

    @Test
    @DisplayName("Should register match result successfully")
    void shouldRegisterMatchResultSuccessfully() {
        // Given
        SuperstarId superstar1 = SuperstarId.create();
        SuperstarId superstar2 = SuperstarId.create();
        List<SuperstarId> participants = List.of(superstar1, superstar2);
        MatchId matchId = show.scheduleMatch(1, participants);
        MatchResult result = new MatchResult(superstar1);

        // When & Then
        assertDoesNotThrow(() -> show.registerResult(matchId, result));
    }

    @Test
    @DisplayName("Should throw MatchNotFoundException when registering result for non-existent match")
    void shouldThrowMatchNotFoundExceptionWhenRegisteringResultForNonExistentMatch() {
        // Given
        MatchId nonExistentMatchId = MatchId.create();
        SuperstarId winner = SuperstarId.create();
        MatchResult result = new MatchResult(winner);

        // When & Then
        MatchNotFoundException exception = assertThrows(
                MatchNotFoundException.class,
                () -> show.registerResult(nonExistentMatchId, result)
        );

        assertEquals("Match not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should schedule match and register result in complete flow")
    void shouldScheduleMatchAndRegisterResultInCompleteFlow() {
        // Given
        SuperstarId superstar1 = SuperstarId.create();
        SuperstarId superstar2 = SuperstarId.create();
        SuperstarId superstar3 = SuperstarId.create();
        List<SuperstarId> participants = List.of(superstar1, superstar2, superstar3);

        // When
        MatchId matchId = show.scheduleMatch(1, participants);
        MatchResult result = new MatchResult(superstar1);

        // Then
        assertNotNull(matchId);
        assertDoesNotThrow(() -> show.registerResult(matchId, result));
    }

    @Test
    @DisplayName("Should return the correct date")
    void shouldReturnTheCorrectDate() {
        // When
        LocalDate returnedDate = show.getDate();

        // Then
        assertEquals(showDate, returnedDate);
    }

    @Test
    @DisplayName("Should return the correct ShowId")
    void shouldReturnTheCorrectShowId() {
        // When
        ShowId showId = show.getId();

        // Then
        assertNotNull(showId);
    }

    @Test
    @DisplayName("Should schedule matches with different orders")
    void shouldScheduleMatchesWithDifferentOrders() {
        // Given
        List<SuperstarId> participants1 = List.of(SuperstarId.create(), SuperstarId.create());
        List<SuperstarId> participants2 = List.of(SuperstarId.create(), SuperstarId.create());
        List<SuperstarId> participants3 = List.of(SuperstarId.create(), SuperstarId.create());

        // When
        MatchId matchId1 = show.scheduleMatch(1, participants1);
        MatchId matchId2 = show.scheduleMatch(2, participants2);
        MatchId matchId3 = show.scheduleMatch(3, participants3);

        // Then
        assertNotNull(matchId1);
        assertNotNull(matchId2);
        assertNotNull(matchId3);
        assertNotEquals(matchId1, matchId2);
        assertNotEquals(matchId2, matchId3);
        assertNotEquals(matchId1, matchId3);
    }

    @Test
    @DisplayName("Should register results for multiple matches")
    void shouldRegisterResultsForMultipleMatches() {
        // Given
        SuperstarId superstar1 = SuperstarId.create();
        SuperstarId superstar2 = SuperstarId.create();
        SuperstarId superstar3 = SuperstarId.create();
        SuperstarId superstar4 = SuperstarId.create();

        List<SuperstarId> participants1 = List.of(superstar1, superstar2);
        List<SuperstarId> participants2 = List.of(superstar3, superstar4);

        MatchId matchId1 = show.scheduleMatch(1, participants1);
        MatchId matchId2 = show.scheduleMatch(2, participants2);

        MatchResult result1 = new MatchResult(superstar1);
        MatchResult result2 = new MatchResult(superstar3);

        // When & Then
        assertDoesNotThrow(() -> show.registerResult(matchId1, result1));
        assertDoesNotThrow(() -> show.registerResult(matchId2, result2));
    }
}

