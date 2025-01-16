package com.nebarrow.service.match;

import com.nebarrow.dto.response.MatchScore;
import com.nebarrow.entity.PlayerStats;

import java.util.Arrays;
import java.util.List;

public class MatchScoringProcessor {

    private final List<IScoringRule> rules = Arrays.asList(new SimpleRule(), new DeuceRule(), new TieBreakRule());

    public void process(MatchScore match, PlayerStats player) {
        for (var rule : rules) {
            if (rule.isApplicable(match, player)) {
                rule.apply(match, player);
                break;
            }
        }
    }
}
