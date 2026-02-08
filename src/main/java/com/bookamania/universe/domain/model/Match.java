package com.bookamania.universe.domain.model;

import com.bookamania.universe.domain.exception.MatchAlreadyFinishedException;
import com.bookamania.universe.domain.exception.MatchNumberOfParticipantsException;
import com.bookamania.universe.domain.model.valueobject.MatchId;
import com.bookamania.universe.domain.model.valueobject.SuperstarId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Match {

    private final MatchId id;
    private final int order;
    private final List<SuperstarId> participants;
    private MatchResult result;

    private Match(MatchId id, int order, List<SuperstarId> participants) {
        this.id = id;
        this.order = order;
        this.participants = participants;
    }

    public static Match rehydrate(MatchId id, int order, List<SuperstarId> participants, MatchResult result) {
        return new Match(id, order, participants, result);
    }

    public static Match create(int order, List<SuperstarId> participants) {
        if (participants.size() < 2) {
            throw new MatchNumberOfParticipantsException("Match must have at least 2 participants");
        }
        return new Match(MatchId.create(), order, participants);
    }

    public void finish(MatchResult result) {
        if (this.result != null) {
            throw new MatchAlreadyFinishedException("Match is already finished");
        }
        this.result = result;
    }

}
