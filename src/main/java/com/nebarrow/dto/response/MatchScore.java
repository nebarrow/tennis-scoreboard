package com.nebarrow.dto.response;

import com.nebarrow.entity.PlayerStats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MatchScore {

    private final List<PlayerStats> players;

    public PlayerStats getWinner() {
        return players.stream()
                .filter(PlayerStats::isWinner)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can't find winner"));
    }

    public boolean isTieBreak() {
        return players.stream().allMatch(player -> player.getGames() == 6);
    }

    public boolean isDeuce() {
        return players.stream().map(PlayerStats::getPoints).distinct().sorted()
                .toList().equals(List.of(3, 4));
    }

    public int getDifferenceBetweenPlayerPoints(PlayerStats player) {
        return player.getPoints() - getOpponent(player).getPoints();
    }

    public int getDifferenceBetweenPlayerGames(PlayerStats player) {
        return player.getGames() - getOpponent(player).getGames();
    }

    public void resetOppositeGames(PlayerStats player) {
        getOpponent(player).resetGames();
    }

    public void resetOppositePoints(PlayerStats player) {
        getOpponent(player).resetPoints();
    }

    public void decrementDeuceOppositePoints(PlayerStats player) {
        getOpponent(player).decrementPoints();
    }

    public PlayerStats getById(int playerId) {
        return players.stream()
                .filter(p -> p.getId() == playerId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown player"));
    }

    public void replacePointsFromPlayerStat(PlayerStats player) {
        for (var p : players) {
            if (p.getId() == player.getId()) {
                p.setSets(player.getSets());
                p.setGames(player.getGames());
                p.setPoints(player.getPoints());
                break;
            }
        }
    }

    private PlayerStats getOpponent(PlayerStats player) {
        return players.stream()
                .filter(opponent -> !opponent.equals(player))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown player"));
    }
}
