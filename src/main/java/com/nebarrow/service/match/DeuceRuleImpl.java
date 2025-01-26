package com.nebarrow.service.match;

import com.nebarrow.model.common.MatchScore;
import com.nebarrow.model.common.PlayerStats;

public class DeuceRuleImpl implements ScoringRule {

    @Override
    public boolean isApplicable(MatchScore match, PlayerStats player) {
        return match.isDeuce();
    }

    @Override
    public void apply(MatchScore match, PlayerStats player) {
        if (player.getPoints() == 3) {
            match.decrementDeuceOppositePoints(player);
            return;
        }
        player.addGame();
        match.resetOppositePoints(player);
    }
}
