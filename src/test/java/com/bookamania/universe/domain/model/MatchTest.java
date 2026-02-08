package com.bookamania.universe.domain.model;

import com.bookamania.universe.domain.exception.MatchAlreadyFinishedException;
import com.bookamania.universe.domain.exception.MatchNumberOfParticipantsException;
import com.bookamania.universe.domain.model.valueobject.MatchId;
import com.bookamania.universe.domain.model.valueobject.SuperstarId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    @DisplayName("Should create a match with valid participants")
    void shouldCreateMatchWithValidParticipants() {
        // Given
        int order = 1;
        List<SuperstarId> participants = List.of(
                SuperstarId.create(),
                SuperstarId.create()
        );

        // When
        Match match = Match.create(order, participants);

        // Then
        assertNotNull(match);
        assertNotNull(match.getId());
    }

    @Test
    @DisplayName("Should create a match with two participants")
    void shouldCreateMatchWithTwoParticipants() {
        // Given
        int order = 1;
        SuperstarId superstar1 = SuperstarId.create();
        SuperstarId superstar2 = SuperstarId.create();
        List<SuperstarId> participants = List.of(superstar1, superstar2);

        // When
        Match match = Match.create(order, participants);

        // Then
        assertNotNull(match);
        assertNotNull(match.getId());
    }

    @Test
    @DisplayName("Should create a match with multiple participants")
    void shouldCreateMatchWithMultipleParticipants() {
        // Given
        int order = 1;
        List<SuperstarId> participants = List.of(
                SuperstarId.create(),
                SuperstarId.create(),
                SuperstarId.create(),
                SuperstarId.create()
        );

        // When
        Match match = Match.create(order, participants);

        // Then
        assertNotNull(match);
        assertNotNull(match.getId());
    }

    @Test
    @DisplayName("Should generate unique MatchId for each match")
    void shouldGenerateUniqueMatchIdForEachMatch() {
        // Given
        int order = 1;
        List<SuperstarId> participants1 = List.of(SuperstarId.create(), SuperstarId.create());
        List<SuperstarId> participants2 = List.of(SuperstarId.create(), SuperstarId.create());

        // When
        Match match1 = Match.create(order, participants1);
        Match match2 = Match.create(order, participants2);

        // Then
        assertNotNull(match1.getId());
        assertNotNull(match2.getId());
        assertNotEquals(match1.getId(), match2.getId());
    }

    @Test
    @DisplayName("Should throw MatchNumberOfParticipantsException when creating match with less than 2 participants")
    void shouldThrowExceptionWhenCreatingMatchWithLessThanTwoParticipants() {
        // Given
        int order = 1;
        List<SuperstarId> participants = List.of(SuperstarId.create());

        // When & Then
        MatchNumberOfParticipantsException exception = assertThrows(
                MatchNumberOfParticipantsException.class,
                () -> Match.create(order, participants)
        );

        assertEquals("Match must have at least 2 participants", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw MatchNumberOfParticipantsException when creating match with empty participants list")
    void shouldThrowExceptionWhenCreatingMatchWithEmptyParticipantsList() {
        // Given
        int order = 1;
        List<SuperstarId> participants = new ArrayList<>();

        // When & Then
        MatchNumberOfParticipantsException exception = assertThrows(
                MatchNumberOfParticipantsException.class,
                () -> Match.create(order, participants)
        );

        assertEquals("Match must have at least 2 participants", exception.getMessage());
    }

    @Test
    @DisplayName("Should finish match successfully with a result")
    void shouldFinishMatchSuccessfullyWithResult() {
        // Given
        SuperstarId superstar1 = SuperstarId.create();
        SuperstarId superstar2 = SuperstarId.create();
        List<SuperstarId> participants = List.of(superstar1, superstar2);
        Match match = Match.create(1, participants);
        MatchResult result = new MatchResult(superstar1);

        // When & Then
        assertDoesNotThrow(() -> match.finish(result));
    }

    @Test
    @DisplayName("Should throw MatchAlreadyFinishedException when trying to finish an already finished match")
    void shouldThrowExceptionWhenTryingToFinishAlreadyFinishedMatch() {
        // Given
        SuperstarId superstar1 = SuperstarId.create();
        SuperstarId superstar2 = SuperstarId.create();
        List<SuperstarId> participants = List.of(superstar1, superstar2);
        Match match = Match.create(1, participants);
        MatchResult result1 = new MatchResult(superstar1);
        MatchResult result2 = new MatchResult(superstar2);

        match.finish(result1);

        // When & Then
        MatchAlreadyFinishedException exception = assertThrows(
                MatchAlreadyFinishedException.class,
                () -> match.finish(result2)
        );

        assertEquals("Match is already finished", exception.getMessage());
    }

    @Test
    @DisplayName("Should return the correct MatchId")
    void shouldReturnTheCorrectMatchId() {
        // Given
        List<SuperstarId> participants = List.of(SuperstarId.create(), SuperstarId.create());
        Match match = Match.create(1, participants);

        // When
        MatchId matchId = match.getId();

        // Then
        assertNotNull(matchId);
    }

    @Test
    @DisplayName("Should create matches with different orders")
    void shouldCreateMatchesWithDifferentOrders() {
        // Given
        List<SuperstarId> participants1 = List.of(SuperstarId.create(), SuperstarId.create());
        List<SuperstarId> participants2 = List.of(SuperstarId.create(), SuperstarId.create());

        // When
        Match match1 = Match.create(1, participants1);
        Match match2 = Match.create(2, participants2);
        Match match3 = Match.create(3, participants1);

        // Then
        assertNotNull(match1);
        assertNotNull(match2);
        assertNotNull(match3);
        assertNotEquals(match1.getId(), match2.getId());
        assertNotEquals(match2.getId(), match3.getId());
    }

    @Test
    @DisplayName("Should create match and finish it in complete flow")
    void shouldCreateMatchAndFinishItInCompleteFlow() {
        // Given
        SuperstarId superstar1 = SuperstarId.create();
        SuperstarId superstar2 = SuperstarId.create();
        SuperstarId superstar3 = SuperstarId.create();
        List<SuperstarId> participants = List.of(superstar1, superstar2, superstar3);

        // When
        Match match = Match.create(1, participants);
        MatchResult result = new MatchResult(superstar1);

        // Then
        assertNotNull(match);
        assertNotNull(match.getId());
        assertDoesNotThrow(() -> match.finish(result));
    }

    @Test
    @DisplayName("Should create match with exactly two participants at minimum")
    void shouldCreateMatchWithExactlyTwoParticipantsAtMinimum() {
        // Given
        int order = 5;
        List<SuperstarId> participants = List.of(
                SuperstarId.create(),
                SuperstarId.create()
        );

        // When
        Match match = Match.create(order, participants);

        // Then
        assertNotNull(match);
        assertNotNull(match.getId());
    }

    @Test
    @DisplayName("Should finish match with winner from participants")
    void shouldFinishMatchWithWinnerFromParticipants() {
        // Given
        SuperstarId winner = SuperstarId.create();
        SuperstarId loser = SuperstarId.create();
        List<SuperstarId> participants = List.of(winner, loser);
        Match match = Match.create(1, participants);
        MatchResult result = new MatchResult(winner);

        // When & Then
        assertDoesNotThrow(() -> match.finish(result));
    }

    @Test
    @DisplayName("Should create multiple matches with same order but different ids")
    void shouldCreateMultipleMatchesWithSameOrderButDifferentIds() {
        // Given
        int sameOrder = 1;
        List<SuperstarId> participants1 = List.of(SuperstarId.create(), SuperstarId.create());
        List<SuperstarId> participants2 = List.of(SuperstarId.create(), SuperstarId.create());

        // When
        Match match1 = Match.create(sameOrder, participants1);
        Match match2 = Match.create(sameOrder, participants2);

        // Then
        assertNotNull(match1.getId());
        assertNotNull(match2.getId());
        assertNotEquals(match1.getId(), match2.getId());
    }
}

