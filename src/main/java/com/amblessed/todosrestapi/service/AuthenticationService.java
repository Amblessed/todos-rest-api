package com.amblessed.todosrestapi.service;



/*
 * @Project Name: todos-rest-api
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17-Sep-25
 */


import com.amblessed.todosrestapi.request.AuthenticationRequest;
import com.amblessed.todosrestapi.response.AuthenticationResponse;
import com.amblessed.todosrestapi.request.RegisterRequest;

public interface AuthenticationService {

    void register(RegisterRequest registerRequest);
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
