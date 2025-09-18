package com.amblessed.todosrestapi.request;



/*
 * @Project Name: todos-rest-api
 * @Author: Okechukwu Bright Onwumere
 * @Created: 18-Sep-25
 */


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class PasswordChangeRequest {

    @NotNull(message = "Old password is required")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String oldPassword;

    @NotNull(message = "New password is required")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String newPassword;

    @NotNull(message = "Confirm password is required")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String confirmPassword;
}
