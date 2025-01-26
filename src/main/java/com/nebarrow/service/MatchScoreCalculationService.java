package com.nebarrow.service;

import com.nebarrow.model.common.MatchScore;
import com.nebarrow.model.common.PlayerStats;
import com.nebarrow.service.match.MatchScoringProcessor;

public class MatchScoreCalculationService {

    private final MatchScoringProcessor matchScoringProcessor = new MatchScoringProcessor();

    public void calculate(PlayerStats player, MatchScore matchScore) {
        addPointsToPlayer(player, matchScore);
        loadStatsToGeneralStats(player, matchScore);
    }

    private void addPointsToPlayer(PlayerStats player, MatchScore matchScore) {
        matchScoringProcessor.process(matchScore, player);
    }

    private void loadStatsToGeneralStats(PlayerStats player, MatchScore matchScore) {
       matchScore.replacePointsFromPlayerStat(player);
    }
}
