package com.nebarrow.dto.response;

import com.nebarrow.entity.Player;
import lombok.Builder;

@Builder
public record MatchResponse(Player playerOne, Player playerTwo, Player winner) {
}
