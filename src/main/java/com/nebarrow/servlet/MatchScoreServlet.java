package com.nebarrow.servlet;

import com.nebarrow.dto.request.MatchRequest;
import com.nebarrow.model.common.MatchScore;
import com.nebarrow.service.FinishedMatchPersistenceService;
import com.nebarrow.service.MatchScoreCalculationService;
import com.nebarrow.service.OngoingMatchService;
import com.nebarrow.util.ServiceLocator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private final static int PLAYER_ONE_ID = 0;
    private final static int PLAYER_TWO_ID = 1;

    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchPersistenceService finishedMatchPersistenceService;
    private OngoingMatchService ongoingMatchService;

    @Override
    public void init() {
        matchScoreCalculationService = ServiceLocator.getService(MatchScoreCalculationService.class);
        ongoingMatchService = ServiceLocator.getService(OngoingMatchService.class);
        finishedMatchPersistenceService = ServiceLocator.getService(FinishedMatchPersistenceService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var matchId = UUID.fromString(req.getParameter("uuid"));
        var score = ongoingMatchService.getMatchScore(matchId);
        forwardToScorePage(req, resp, matchId, score);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var playerId = Integer.parseInt(req.getParameter("player"));
        var matchId = UUID.fromString(req.getParameter("uuid"));

        if (processMatch(playerId, matchId)) {
            resp.sendRedirect(req.getContextPath() + "/matches");
            return;
        }

        var score = ongoingMatchService.getMatchScore(matchId);
        forwardToScorePage(req, resp, matchId, score);

    }

    private void saveMatch(MatchScore score) {
        var match = new MatchRequest(score.getPlayers().get(PLAYER_ONE_ID).getId(),
                score.getPlayers().get(PLAYER_TWO_ID).getId(),
                score.getWinner().getId());
        finishedMatchPersistenceService.save(match);
    }

    private void forwardToScorePage(HttpServletRequest req, HttpServletResponse resp, UUID matchId, MatchScore score)
            throws ServletException, IOException {
        req.setAttribute("uuid", matchId);
        req.setAttribute("matchScore", score);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    private boolean processMatch(int playerId, UUID matchId) {
        var player = ongoingMatchService.getPlayerStatById(matchId, playerId);
        if (player == null) {
            return false;
        }

        var score = ongoingMatchService.getMatchScore(matchId);
        matchScoreCalculationService.calculate(player, score);

        if (ongoingMatchService.isMatchOver(matchId)) {
            saveMatch(score);
            ongoingMatchService.removeMatch(matchId);
            return true;
        }
        return false;
    }
}
