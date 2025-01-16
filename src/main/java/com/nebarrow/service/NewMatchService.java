package com.nebarrow.service;

import com.nebarrow.dto.request.NewMatchRequest;
import com.nebarrow.dto.response.NewMatchResponse;
import com.nebarrow.entity.Player;
import com.nebarrow.mapper.INewMatchMapper;
import com.nebarrow.repository.PlayerRepository;

public class NewMatchService {

    private final PlayerRepository playerRepository = new PlayerRepository(Player.class);

    public NewMatchResponse save(NewMatchRequest request) {
        var mapper = new INewMatchMapper();
        var playerOne = mapper.toEntity(request.firstPlayerName());
        var playerTwo = mapper.toEntity(request.secondPlayerName());
        playerOne = playerRepository.save(playerOne);
        playerTwo = playerRepository.save(playerTwo);
        return new NewMatchResponse(
                playerOne.getId(),
                playerTwo.getId());
    }
}
