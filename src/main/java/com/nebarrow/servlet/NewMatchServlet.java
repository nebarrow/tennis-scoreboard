package com.nebarrow.servlet;

import com.nebarrow.dto.request.NewMatchRequest;
import com.nebarrow.entity.CurrentMatch;
import com.nebarrow.entity.Match;
import com.nebarrow.entity.Score;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var players = (NewMatchRequest) req.getAttribute("Players");
        var match = new CurrentMatch(
                UUID.randomUUID(),
                players.firstPlayerName(),
                players.secondPlayerName(),
                new Score(0,0));
        Map<UUID, CurrentMatch> matches = new HashMap<>();
        matches.put(match.getID(),match);
        resp.sendRedirect("/match-score?uuid=" + match.getID());
    }
}
