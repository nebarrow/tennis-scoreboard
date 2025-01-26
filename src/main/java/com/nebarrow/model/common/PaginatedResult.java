package com.nebarrow.model.common;

import com.nebarrow.dto.response.MatchResponse;

import java.util.List;

public record PaginatedResult(List<MatchResponse> items, int totalPage, int currentPage) {

}
