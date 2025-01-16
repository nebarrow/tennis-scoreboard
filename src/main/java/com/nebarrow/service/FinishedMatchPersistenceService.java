package com.nebarrow.service;

import com.nebarrow.dto.request.MatchRequest;
import com.nebarrow.dto.request.MatchWinnerRequest;
import com.nebarrow.dto.response.MatchResponse;
import com.nebarrow.entity.Match;
import com.nebarrow.mapper.MatchMapper;
import com.nebarrow.repository.MatchRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FinishedMatchPersistenceService {
    private final MatchRepository matchRepository = new MatchRepository(Match.class);

    public void save(MatchRequest request) {
        matchRepository.save(MatchMapper.toEntity(request));
    }

    public List<MatchResponse> getAll() {
        return matchRepository.getAll().stream()
                .map(MatchMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MatchResponse> getByName(MatchWinnerRequest winner) {
        return getAll().stream()
                .filter(match -> match.playerOne().getName().contains(winner.name()) || match.playerTwo().getName().contains(winner.name()))
                .toList();
    }
}
