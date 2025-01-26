package com.nebarrow.servlet;

import com.nebarrow.dto.request.MatchWinnerRequest;
import com.nebarrow.dto.response.MatchResponse;
import com.nebarrow.service.FinishedMatchPersistenceService;
import com.nebarrow.service.PaginationService;
import com.nebarrow.util.ServiceLocator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private FinishedMatchPersistenceService finishedMatchService;
    private PaginationService paginationService;

    @Override
    public void init() {
        finishedMatchService = ServiceLocator.getService(FinishedMatchPersistenceService.class);
        paginationService = new PaginationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var filterInput = req.getParameter("filter");

        var matches = getFilteredMatches(filterInput);
        var page = getPageNumber(req);
        var paginatedResult = paginationService.paginate(matches, page);

        req.setAttribute("matches", paginatedResult.items());
        req.setAttribute("currentPage", paginatedResult.currentPage());
        req.setAttribute("totalPages", paginatedResult.totalPage());

        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }

    private int getPageNumber(HttpServletRequest req) {
        var pageParam = req.getParameter("page");
        return (pageParam == null || pageParam.isBlank()) ? DEFAULT_PAGE_NUMBER : Integer.parseInt(pageParam);
    }

    private List<MatchResponse> getFilteredMatches(String filterInput) {
        return (filterInput == null || filterInput.isBlank())
                ? finishedMatchService.getAll()
                : finishedMatchService.getByName(new MatchWinnerRequest(filterInput));
    }
}
