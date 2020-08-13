package com.riqra.assessment.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.riqra.assessment.domain.entities.ApplicationUser;
import com.riqra.assessment.domain.services.ApplicationUserService;
import com.riqra.assessment.security.handlers.JWTAuthenticationFailureHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.riqra.assessment.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final Logger authLogger = LoggerFactory.getLogger(this.getClass());

    private final AuthenticationManager authenticationManager;
    private final ApplicationUserService applicationUserService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                                   ApplicationUserService applicationUserService) {
        this.authenticationManager = authenticationManager;
        this.applicationUserService = applicationUserService;
        super.setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            ApplicationUser credentials = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);

            String requestPassword = credentials.getPassword();

            if (Boolean.FALSE.equals(applicationUserService.isUserRegistered(credentials.getEmail()))) {
                authLogger.info("User doesn't exist");
                credentials = applicationUserService.signUp(credentials.getEmail(), credentials.getPassword());
            }

            authLogger.info("Authenticating user...");

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(),
                            requestPassword,
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
