package com.amblessed.todosrestapi.utils;



/*
 * @Project Name: todos-rest-api
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17-Sep-25
 */


import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretGenerator {

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32]; // 256-bit key
        random.nextBytes(bytes);
        // Base64URL encoding without padding
        String secret = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        System.out.println("Generated JWT Secret: " + secret);
    }
}
