package com.theo.ecommerce_back.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.theo.ecommerce_back.model.LocalUser;
import com.theo.ecommerce_back.repository.LocalUserRepository;
import com.theo.ecommerce_back.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private LocalUserRepository localUserRepository;

    public JWTRequestFilter(JWTService jwtService, LocalUserRepository localUserRepository){
        this.jwtService = jwtService;
        this.localUserRepository = localUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")){
            String token = tokenHeader.substring(7);
            try {
                String username = jwtService.getUsername(token);
                Optional<LocalUser>opUser = localUserRepository.findByUsernameIgnoreCase(username);
                if(opUser.isPresent()){
                    LocalUser user = opUser.get();
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }catch (JWTDecodeException e){

            }
        }
        filterChain.doFilter(request, response);
    }




















}
