package com.nebarrow.service.match;

import com.nebarrow.dto.response.MatchScore;
import com.nebarrow.entity.PlayerStats;

public interface IScoringRule {

    boolean isApplicable(MatchScore match, PlayerStats player);

    void apply(MatchScore match, PlayerStats player);
}
