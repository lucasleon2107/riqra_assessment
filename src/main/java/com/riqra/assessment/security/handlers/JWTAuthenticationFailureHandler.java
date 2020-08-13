package com.riqra.assessment.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riqra.assessment.domain.dtos.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        logger.info("Authentication failed");

        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

        ApiResponse apiResponse = new ApiResponse("Oops! wrong password");

        OutputStream out = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, apiResponse);
        out.flush();
    }
}
