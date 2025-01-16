package com.nebarrow.service;

import com.nebarrow.dto.response.MatchScore;
import com.nebarrow.entity.PlayerStats;
import com.nebarrow.service.match.MatchScoringProcessor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatchScoreCalculationService {

    private final PlayerStats player;
    private final MatchScore matchScore;
    private final MatchScoringProcessor matchScoringProcessor = new MatchScoringProcessor();

    public void startMatch() {
        addPointsToPlayer();
        loadStatsToGeneralStats();
    }

    public boolean isOver() {
        return player.isWinner();
    }

    private void addPointsToPlayer() {
        matchScoringProcessor.process(matchScore, player);
    }

    private void loadStatsToGeneralStats() {
        matchScore.replacePointsFromPlayerStat(player);
    }
}
