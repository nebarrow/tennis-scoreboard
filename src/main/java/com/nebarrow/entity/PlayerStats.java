package com.nebarrow.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerStats {
    private int id;
    private String name;
    private int points;
    private int sets;
    private int games;
    private boolean isWinner;

    public void addPoint() {
        points++;
    }

    public void addGame() {
        games++;
        points = 0;
    }

    public void addSet() {
        sets++;
        games = 0;
        points = 0;
        if (sets == 2) {
            isWinner = true;
            sets = 0;
            resetGames();
            resetPoints();
        }
    }

    public void resetPoints() {
        points = 0;
    }

    public void decrementPoints() {
        points--;
    }

    public void resetGames() {
        points = 0;
        games = 0;
    }

}
