package com.amblessed.todosrestapi.service;



/*
 * @Project Name: employees
 * @Author: Okechukwu Bright Onwumere
 * @Created: 16-Sep-25
 */


import com.amblessed.todosrestapi.entity.User;
import com.amblessed.todosrestapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user: {}", username);
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        if (!user.isEnabled()) {
            throw new UsernameNotFoundException("User is inactive: " + username);
        }
        return user;
    }
}
