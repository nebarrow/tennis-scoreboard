package com.nebarrow.service.match;

import com.nebarrow.dto.response.MatchScore;
import com.nebarrow.entity.PlayerStats;

public class SimpleRule implements IScoringRule {

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
