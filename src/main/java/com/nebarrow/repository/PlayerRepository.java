package com.nebarrow.repository;

import com.nebarrow.entity.Player;

public class PlayerRepository extends BaseRepository<Integer, Player> {
    public PlayerRepository(Class<Player> entityClass) {
        super(entityClass);
    }
}
