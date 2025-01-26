package com.nebarrow.service.match;

import com.nebarrow.model.common.MatchScore;
import com.nebarrow.model.common.PlayerStats;

public interface ScoringRule {

    boolean isApplicable(MatchScore match, PlayerStats player);

    void apply(MatchScore match, PlayerStats player);
}
