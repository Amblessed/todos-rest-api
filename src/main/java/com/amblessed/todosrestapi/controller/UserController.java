package com.amblessed.todosrestapi.controller;



/*
 * @Project Name: todos-rest-api
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17-Sep-25
 */


import com.amblessed.todosrestapi.request.PasswordChangeRequest;
import com.amblessed.todosrestapi.response.UserResponse;
import com.amblessed.todosrestapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@Tag(name = "User REST API Endpoints", description = "User Controller to handle user operations")
public class UserController {

    private final UserService userService;

    @Operation(summary = "User Information", description = "Get current user info")
    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserInfo() {
        return ResponseEntity.ok(userService.getUserInfo());
    }


    @Operation(summary = "Delete User", description = "Delete current user details")
    @DeleteMapping
    public ResponseEntity<String> deleteUser() {
        userService.deleteUser();
        return ResponseEntity.ok("User deleted successfully");
    }


    @Operation(summary = "Update Password", description = "Update current user password after authentication")
    @PutMapping("/change-password")
    public ResponseEntity<String> updatePassword(@Valid @RequestBody PasswordChangeRequest passwordChangeRequest) {
        userService.changePassword(passwordChangeRequest);
        return ResponseEntity.ok("Password updated successfully");
    }
}
