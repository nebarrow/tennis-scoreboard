package com.nebarrow.repository;

import com.nebarrow.model.entity.Match;

public class MatchRepository extends BaseRepository<Integer, Match> {
    public MatchRepository(Class<Match> entityClass) {
        super(entityClass);
    }
}
