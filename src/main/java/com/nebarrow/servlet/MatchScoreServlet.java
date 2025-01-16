package com.nebarrow.servlet;

import com.nebarrow.dto.request.MatchRequest;
import com.nebarrow.service.FinishedMatchPersistenceService;
import com.nebarrow.service.MatchScoreCalculationService;
import com.nebarrow.service.OngoingMatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private final int PLAYER_TWO_ID = 1;

    private OngoingMatchService ongoingMatchService = OngoingMatchService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var matchId = UUID.fromString(req.getParameter("uuid"));
        var score = ongoingMatchService.getMatchScore(matchId);
        req.setAttribute("matchScore", score);
        req.setAttribute("uuid", matchId);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    private MatchScoreCalculationService gameService;
    private FinishedMatchPersistenceService matchService = new FinishedMatchPersistenceService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var playerId = Integer.parseInt(req.getParameter("player"));
        var matchId = UUID.fromString(req.getParameter("uuid"));

        var player = ongoingMatchService.getPlayerStatById(matchId, playerId);
        if (player == null) {
            resp.sendRedirect("/matches");
            return;
        }
        var score = ongoingMatchService.getMatchScore(matchId);
        req.setAttribute("uuid", matchId);
        req.setAttribute("matchScore", score);

        gameService = new MatchScoreCalculationService(player, score);
        gameService.startMatch();


        if (gameService.isOver()) {
            var match = new MatchRequest(
                    score.getPlayers().getFirst().getId(),
                    score.getPlayers().get(PLAYER_TWO_ID).getId(),
                    score.getWinner().getId());
            matchService.save(match);
            resp.sendRedirect("/matches");
            ongoingMatchService.removeMatch(matchId);
            return;
        }
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);

    }
}
