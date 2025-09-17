package com.amblessed.todosrestapi.security;



/*
 * @Project Name: employees
 * @Author: Okechukwu Bright Onwumere
 * @Created: 11-Sep-25
 */


import com.amblessed.todosrestapi.exception.GlobalExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {

    private final Logger log = LoggerFactory.getLogger(CustomAuthEntryPoint.class);

    private final GlobalExceptionHandler exceptionHandler;

    public CustomAuthEntryPoint(GlobalExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String ip = request.getRemoteAddr();
        String path = request.getRequestURI();
        log.info("Unauthorized access to {}, from IP: {}", path , ip);
        if (auth != null) {
            log.info("User: {}, Role: {}", auth.getName() , auth.getAuthorities());
        } else {
            log.info("Authentication is null. Request URI: {}", request.getRequestURI());
        }

        ProblemDetail detail = exceptionHandler.createProblemDetail(authException, HttpStatus.UNAUTHORIZED);
        exceptionHandler.writeProblemDetail(response, detail);
    }
}
