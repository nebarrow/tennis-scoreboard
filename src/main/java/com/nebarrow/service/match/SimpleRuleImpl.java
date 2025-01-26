package com.nebarrow.service.match;

import com.nebarrow.model.common.MatchScore;
import com.nebarrow.model.common.PlayerStats;

public class SimpleRuleImpl implements ScoringRule {

    @Override
    public boolean isApplicable(MatchScore match, PlayerStats player) {
        return !match.isDeuce() && !match.isTieBreak();
    }

    @Override
    public void apply(MatchScore match, PlayerStats player) {
        if (player.getPoints() == 3 && match.getDifferenceBetweenPlayerPoints(player) != 0) {
            player.addGame();
            match.resetOppositePoints(player);
            if (player.getGames() >= 6 && match.getDifferenceBetweenPlayerGames(player) >= 2) {
                player.addSet();
                match.resetOppositeGames(player);
            }
            return;
        }
        player.addPoint();
    }
}
