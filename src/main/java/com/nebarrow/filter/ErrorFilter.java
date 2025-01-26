package com.nebarrow.filter;

import com.nebarrow.exception.EntityNotFoundException;
import com.nebarrow.exception.RepositoryException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter
public class ErrorFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(req, res);
        } catch (RepositoryException e) {
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (EntityNotFoundException e) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
