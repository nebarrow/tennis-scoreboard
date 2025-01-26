package com.nebarrow.service;

import com.nebarrow.dto.request.NewMatchRequest;
import com.nebarrow.dto.response.NewMatchResponse;
import com.nebarrow.model.entity.Player;
import com.nebarrow.mapper.NewMatchMapper;
import com.nebarrow.repository.PlayerRepository;

public class NewMatchService {

    private final PlayerRepository playerRepository = new PlayerRepository(Player.class);
    private final NewMatchMapper MAPPER = NewMatchMapper.INSTANCE;

    public NewMatchResponse save(NewMatchRequest request) {
        var playerOne = MAPPER.toEntity(request.firstPlayerName());
        var playerTwo = MAPPER.toEntity(request.secondPlayerName());
        playerOne = playerRepository.save(playerOne);
        playerTwo = playerRepository.save(playerTwo);
        return new NewMatchResponse(
                playerOne.getId(),
                playerTwo.getId());
    }
}
