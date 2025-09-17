package com.amblessed.todosrestapi.service;



/*
 * @Project Name: todos-rest-api
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17-Sep-25
 */


import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {

    String extractUsername(String token);
    String generateToken(Map<String, Object> claims, UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
}
