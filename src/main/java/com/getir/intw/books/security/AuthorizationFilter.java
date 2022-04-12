package com.getir.intw.books.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthorizationFilter extends OncePerRequestFilter {

    List<String> ignoredPaths = Arrays.asList("/customer/register", "/swagger", "v3/api-docs");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (ignoredPaths.stream().anyMatch(path -> request.getServletPath().contains(path))) {
            filterChain.doFilter(request, response);
        }
        else {
            String authHeader = request.getHeader("AUTHORIZATION");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String jwt = authHeader.substring("Bearer ".length());
                // Do jwt stuff here

                filterChain.doFilter(request, response);
            }
            else {
                response.sendError(HttpStatus.UNAUTHORIZED.value());
            }
        }

    }
}
