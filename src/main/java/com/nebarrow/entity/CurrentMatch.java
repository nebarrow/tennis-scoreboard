package com.nebarrow.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CurrentMatch {

    private final UUID ID;
    private final String PLAYER_ONE;
    private final String PLAYER_TWO;
    private Score score;
}
