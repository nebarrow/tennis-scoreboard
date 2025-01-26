package com.nebarrow.service.match;

import com.nebarrow.model.common.MatchScore;
import com.nebarrow.model.common.PlayerStats;

public class TieBreakRuleImpl implements ScoringRule {

    @Override
    public boolean isApplicable(MatchScore match, PlayerStats player) {
        return match.isTieBreak();
    }

    @Override
    public void apply(MatchScore match, PlayerStats player) {
        if (player.getPoints() >= 6 && match.getDifferenceBetweenPlayerPoints(player) >= 1) {
            player.addSet();
            match.resetOppositeGames(player);
            return;
        }
        player.setPoints(player.getPoints() + 1);
    }
}
