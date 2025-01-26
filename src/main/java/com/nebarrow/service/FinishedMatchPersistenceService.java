package com.nebarrow.service;

import com.nebarrow.dto.request.MatchRequest;
import com.nebarrow.dto.request.MatchWinnerRequest;
import com.nebarrow.dto.response.MatchResponse;
import com.nebarrow.model.entity.Match;
import com.nebarrow.mapper.MatchMapper;
import com.nebarrow.repository.MatchRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FinishedMatchPersistenceService {

    private final MatchRepository matchRepository = new MatchRepository(Match.class);
    private final MatchMapper MAPPER = MatchMapper.INSTANCE;

    public void save(MatchRequest request) {
        matchRepository.save(MAPPER.toEntity(request));
    }

    public List<MatchResponse> getAll() {
        return matchRepository.getAll().stream()
                .map(MAPPER::toDto)
                .collect(Collectors.toList());
    }

    public List<MatchResponse> getByName(MatchWinnerRequest winner) {
        return getAll().stream()
                .filter(match -> match.playerOne().contains(winner.name()) || match.playerTwo().contains(winner.name()))
                .toList();
    }
}
