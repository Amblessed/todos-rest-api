package com.amblessed.todosrestapi.service;



/*
 * @Project Name: todos-rest-api
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17-Sep-25
 */


import com.amblessed.todosrestapi.entity.Authority;
import com.amblessed.todosrestapi.entity.User;
import com.amblessed.todosrestapi.exception.AlreadyExistsException;
import com.amblessed.todosrestapi.repository.UserRepository;
import com.amblessed.todosrestapi.request.AuthenticationRequest;
import com.amblessed.todosrestapi.request.AuthenticationResponse;
import com.amblessed.todosrestapi.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(RegisterRequest registerRequest) {

        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new AlreadyExistsException("Email already exists");
        }

        User user = buildNewUser(registerRequest);
        userRepository.save(user);

    }

    @Override
    @Transactional(readOnly = true) // Declares that this method runs within a read-only transaction, meaning it won't modify the database.
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        User user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        String token = jwtService.generateToken(new HashMap<>(), user);
        return new AuthenticationResponse(token);
    }

    private User buildNewUser(RegisterRequest registerRequest){
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setAuthorities(initialAuthority());
        return user;
    }


    private List<Authority> initialAuthority(){
        boolean isFirstUser = userRepository.count() == 0;
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("ROLE_EMPLOYEE"));
        if(isFirstUser){
           authorities.add(new Authority("ROLE_ADMIN"));
        }
        return authorities;
    }
}
