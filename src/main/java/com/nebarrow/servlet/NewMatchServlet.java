package com.nebarrow.servlet;

import com.nebarrow.dto.request.NewMatchRequest;
import com.nebarrow.dto.response.MatchScore;
import com.nebarrow.dto.response.NewMatchResponse;
import com.nebarrow.entity.PlayerStats;
import com.nebarrow.service.NewMatchService;
import com.nebarrow.service.OngoingMatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    private final NewMatchService service = new NewMatchService();
    private final OngoingMatchService matchService = OngoingMatchService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var players = (NewMatchRequest) req.getAttribute("Players");
        var playersDto = (NewMatchResponse) service.save(players);
        var playerOneStats = PlayerStats.builder()
                .id(playersDto.playerOneId())
                .name(players.firstPlayerName())
                .build();
        var playerTwoStats = PlayerStats.builder()
                .id(playersDto.playerTwoId())
                .name(players.secondPlayerName())
                .build();

        var matchScore = MatchScore.builder()
                .players(new ArrayList<>(List.of(playerOneStats, playerTwoStats)))
                .build();


        var uuid = matchService.addMatch(matchScore);
        resp.sendRedirect("/match-score?uuid=" + uuid);
    }
}
