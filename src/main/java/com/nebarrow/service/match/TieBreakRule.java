package com.nebarrow.service.match;

import com.nebarrow.dto.response.MatchScore;
import com.nebarrow.entity.PlayerStats;

public class TieBreakRule implements IScoringRule {

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
