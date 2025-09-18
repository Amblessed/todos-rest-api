package com.amblessed.todosrestapi.service;



/*
 * @Project Name: todos-rest-api
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17-Sep-25
 */


import com.amblessed.todosrestapi.entity.Authority;
import com.amblessed.todosrestapi.entity.User;
import com.amblessed.todosrestapi.repository.UserRepository;
import com.amblessed.todosrestapi.request.PasswordChangeRequest;
import com.amblessed.todosrestapi.response.UserResponse;
import com.amblessed.todosrestapi.utils.FindAuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final FindAuthenticatedUser findAuthenticatedUser;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserInfo() {
        User user = findAuthenticatedUser.getAuthenticatedUser();
        return new UserResponse(
                user.getId(), user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                user.getAuthorities().stream().map(Authority.class::cast).toList());
    }

    @Override
    public void deleteUser() {
        User user = findAuthenticatedUser.getAuthenticatedUser();
        if(isLastAdmin(user)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin cannot delete itself");
        }
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void changePassword(PasswordChangeRequest passwordChangeRequest) {
        User user = findAuthenticatedUser.getAuthenticatedUser();
        if(!isOldPasswordCorrect(user.getPassword(), passwordChangeRequest.getOldPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Old password is incorrect");
        }

        validatePasswordChangeRequest(passwordChangeRequest);
        user.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
        userRepository.save(user);
    }

    private boolean isOldPasswordCorrect(String currentPassword, String oldPassword){
        return passwordEncoder.matches(oldPassword, currentPassword);
    }

    private void validatePasswordChangeRequest(PasswordChangeRequest passwordChangeRequest){

        if(passwordChangeRequest.getOldPassword().equals(passwordChangeRequest.getNewPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Old password and New password are the same. They should be different");
        }

        if(!passwordChangeRequest.getNewPassword().equals(passwordChangeRequest.getConfirmPassword())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "New password and confirm password do not match");
        }
    }

    private boolean isLastAdmin(User user){
        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        if(isAdmin){
            int adminCount = userRepository.countAdminUsers();
            return adminCount <= 1;
        }
        return false;
    }
}
