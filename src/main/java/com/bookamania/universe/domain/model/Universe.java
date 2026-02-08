package com.bookamania.universe.domain.model;

import com.bookamania.universe.domain.exception.ShowNotFoundException;
import com.bookamania.universe.domain.model.valueobject.MatchId;
import com.bookamania.universe.domain.model.valueobject.ShowId;
import com.bookamania.universe.domain.model.valueobject.SuperstarId;
import com.bookamania.universe.domain.model.valueobject.UniverseId;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Universe {

    private final UniverseId id;
    private final String name;
    private Map<ShowId, Show> shows = new HashMap<>();

    public Universe(UniverseId id, String name) {
        this.id = id;
        this.name = name;
    }

    public ShowId scheduleShow(LocalDate date) {
        Show show = Show.schedule(date);

        shows.put(show.getId(), show);

        return show.getId();
    }

    public MatchId scheduleMatch(ShowId showId, int order,  List<SuperstarId> participants) {
        Show show = getShow(showId);
        return show.scheduleMatch(order, participants);
    }

    public void registerMatchResult(ShowId showId, MatchId matchId, MatchResult result) {
        Show show = getShow(showId);
        show.registerResult(matchId, result);
    }

    private Show getShow(ShowId id) {
        return Optional.ofNullable(shows.get(id))
                .orElseThrow(() -> new ShowNotFoundException("Show not found"));
    }
}
