package com.nebarrow.service;

import com.nebarrow.dto.response.MatchScore;
import com.nebarrow.entity.PlayerStats;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Setter
public class OngoingMatchService {

    private OngoingMatchService() {}

    private static final Map<UUID, MatchScore> matches = new ConcurrentHashMap<>();

    @Getter
    public static OngoingMatchService INSTANCE = new OngoingMatchService();

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
}
