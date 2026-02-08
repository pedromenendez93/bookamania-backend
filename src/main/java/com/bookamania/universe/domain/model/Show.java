package com.bookamania.universe.domain.model;

import com.bookamania.universe.domain.exception.MatchNotFoundException;
import com.bookamania.universe.domain.model.valueobject.MatchId;
import com.bookamania.universe.domain.model.valueobject.ShowId;
import com.bookamania.universe.domain.model.valueobject.SuperstarId;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
public class Show {

    private final ShowId id;
    private final LocalDate date;
    private final Map<MatchId, Match> matches;

    private Show(ShowId id, LocalDate date) {
        this.id = id;
        this.date = date;
        this.matches = new HashMap<>();
    }

    private Show(ShowId id, LocalDate date, Map<MatchId, Match> matches) {
        this.id = id;
        this.date = date;
        this.matches = matches;
    }

    public static Show rehydrate(ShowId id, LocalDate date, List<Match> matches) {
        return new Show(id, date, matches.stream().collect(HashMap::new,
                (m, v) -> m.put(v.getId(), v),
                HashMap::putAll));
    }

    public static Show schedule(LocalDate date) {
        return new Show(ShowId.create(), date);
    }

    public MatchId scheduleMatch(int order, List<SuperstarId> participants) {
        Match match = Match.create(order, participants);
        matches.put(match.getId(), match);
        return match.getId();
    }

    public void registerResult(MatchId matchId, MatchResult matchResult) {
        Match match = getMatch(matchId);
        match.finish(matchResult);
    }

    private Match getMatch(MatchId matchId) {
       return Optional.ofNullable(matches.get(matchId))
               .orElseThrow(() -> new MatchNotFoundException("Match not found"));
    }
}
