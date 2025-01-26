package com.nebarrow.service;

import com.nebarrow.model.common.MatchScore;
import com.nebarrow.model.common.PlayerStats;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Setter
public class OngoingMatchService {

    private static final Map<UUID, MatchScore> matches = new ConcurrentHashMap<>();

    public UUID addMatch(MatchScore matchScore) {
        var matchId = UUID.randomUUID();
        matches.put(matchId, matchScore);
        return matchId;
    }

    public MatchScore getMatchScore(UUID matchId) {
        return matches.get(matchId);
    }

    public PlayerStats getPlayerStatById(UUID matchId, int playerId) {
        if (matches.isEmpty()) {
            return null;
        }
        return matches.get(matchId).getById(playerId);
    }

    public void removeMatch(UUID matchId) {
        matches.remove(matchId);
    }

    public boolean isMatchOver(UUID matchId) {
        return matches.get(matchId).getWinner() != null;
    }
}
