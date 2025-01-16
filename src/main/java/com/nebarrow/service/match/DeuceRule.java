package com.nebarrow.service.match;

import com.nebarrow.dto.response.MatchScore;
import com.nebarrow.entity.PlayerStats;

public class DeuceRule implements IScoringRule {

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
