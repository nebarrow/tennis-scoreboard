package com.nebarrow.mapper;

import com.nebarrow.entity.Player;

public class INewMatchMapper {


    public Player toEntity(String name) {
        return Player.builder().name(name).build();
    }
}
