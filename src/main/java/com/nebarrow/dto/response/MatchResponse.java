package com.nebarrow.dto.response;

import lombok.Builder;

@Builder
public record MatchResponse(String playerOne, String playerTwo, String winner) {
}
