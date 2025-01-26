package com.nebarrow.service.match;

import com.nebarrow.model.common.MatchScore;
import com.nebarrow.model.common.PlayerStats;

import java.util.Arrays;
import java.util.List;

public class MatchScoringProcessor {

    private final List<ScoringRule> rules = Arrays.asList(new SimpleRuleImpl(), new TieBreakRuleImpl(), new DeuceRuleImpl());

    public void process(MatchScore match, PlayerStats player) {
        for (var rule : rules) {
            if (rule.isApplicable(match, player)) {
                rule.apply(match, player);
                break;
            }
        }
    }
}
