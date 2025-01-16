package com.nebarrow.mapper;

import com.nebarrow.dto.request.MatchRequest;
import com.nebarrow.dto.response.MatchResponse;
import com.nebarrow.entity.Match;
import com.nebarrow.entity.Player;

public class MatchMapper {

    public static Match toEntity(MatchRequest request) {
        return Match.builder()
                .playerOne(Player.builder().id(request.playerOne()).build())
                .playerTwo(Player.builder().id(request.playerTwo()).build())
                .winner(Player.builder().id(request.winner()).build())
                .build();
    }

    public static MatchResponse toDto(Match match) {
        return MatchResponse.builder()
                .playerOne(Player.builder().name(match.getPlayerOne().getName()).build())
                .playerTwo(Player.builder().name(match.getPlayerTwo().getName()).build())
                .winner(Player.builder().name(match.getWinner().getName()).build())
                .build();
    }
}
