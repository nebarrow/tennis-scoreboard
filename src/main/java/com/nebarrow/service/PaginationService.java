package com.nebarrow.service;

import com.nebarrow.dto.response.MatchResponse;
import com.nebarrow.model.common.PaginatedResult;

import java.util.List;

public class PaginationService {

    private static final int ITEMS_PER_PAGE = 5;

    public PaginatedResult paginate(List<MatchResponse> items, int currentPage) {
        int totalItems = items.size();

        if (totalItems == 0) {
            return new PaginatedResult(List.of(), 1, 1);
        }

        int totalPages = (int) Math.ceil((double) totalItems / ITEMS_PER_PAGE);
        if (currentPage < 1) {
            currentPage = 1;
        } else if (currentPage > totalPages) {
            currentPage = totalPages;
        }
        int start = (currentPage - 1) * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE, totalItems);

        var paginatedItems = items.subList(start, end);

        return new PaginatedResult(paginatedItems, totalPages, currentPage);
    }
}
