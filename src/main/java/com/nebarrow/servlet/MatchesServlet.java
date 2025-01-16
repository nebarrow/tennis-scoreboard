package com.nebarrow.servlet;

import com.nebarrow.dto.request.MatchWinnerRequest;
import com.nebarrow.service.FinishedMatchPersistenceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    private final FinishedMatchPersistenceService finishedMatch = new FinishedMatchPersistenceService();
    private final int NUMBER_OF_POSTS_PER_PAGE = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var filterInput = req.getParameter("filter");

        var matches = filterInput == null ? finishedMatch.getAll() : finishedMatch.getByName(new MatchWinnerRequest(filterInput));

        int page = Integer.parseInt(req.getParameter("page") == null ? "1" : req.getParameter("page"));
        int totalMatches = matches.size();
        int totalPages = (int) Math.ceil((double) totalMatches / NUMBER_OF_POSTS_PER_PAGE);

        int start = (page - 1) * NUMBER_OF_POSTS_PER_PAGE;
        int end = Math.min(start + NUMBER_OF_POSTS_PER_PAGE, totalMatches);
        var matchesForPage = matches.subList(start, end);

        req.setAttribute("matches", matchesForPage);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }
}
