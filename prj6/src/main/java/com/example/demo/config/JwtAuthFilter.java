package com.example.demo.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain)
            throws ServletException, IOException {

                private final JwtSerivce jwtSerivce;

                final String authHeader = request.getHeader("Authorization");
                final String jwtToken;
                final String userEmail;
                if(authHeader == null || !authHeader.startsWith("Bearer")){
                    filterChain.doFilter(request, response);
                    return;
                }

                jwtToken = authHeader.substring("Bearer".length());
                
                userEmail = jwtSerivce.extractUsername(jwtToken);


    }
    
}
