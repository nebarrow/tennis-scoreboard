package com.nebarrow.filter;

import com.nebarrow.dto.request.NewMatchRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static jakarta.servlet.http.HttpServletResponse.*;
import java.io.IOException;

public class NewMatchFilter extends HttpFilter {
    private final String ALLOWED_METHODS = "POST, GET";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        switch (req.getMethod()) {
            case "POST" -> handlePostRequest(req, res, chain);
            case "GET" -> chain.doFilter(req, res);
            default -> {
                res.setHeader("Allow", ALLOWED_METHODS);
                res.setStatus(SC_METHOD_NOT_ALLOWED);
            }
        }
    }

    private void handlePostRequest(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        var request = new NewMatchRequest(
                req.getParameter("playerOne"),
                req.getParameter("playerTwo"));
        req.setAttribute("Players", request);
        chain.doFilter(req, res);
    }
}
