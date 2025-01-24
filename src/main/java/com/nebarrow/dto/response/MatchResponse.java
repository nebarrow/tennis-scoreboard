package com.nebarrow.dto.response;

import com.nebarrow.entity.Player;
import lombok.Builder;

@Builder
public record MatchResponse(String playerOne, String playerTwo, String winner) {
}
