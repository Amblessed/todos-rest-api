package com.amblessed.todosrestapi.request;



/*
 * @Project Name: todos-rest-api
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17-Sep-25
 */

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotNull(message = "First name is required")
    @Size(min = 3, max = 20, message = "First name must be between 2 and 20 characters")
    private String firstName;

    @NotNull(message = "First name is required")
    @Size(min = 3, max = 20, message = "First name must be between 2 and 20 characters")
    private String lastName;

    @NotNull(message = "Email is required")
    @Email
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 8, max = 16, message = "Password must be at least 8 characters long")
    private String password;
}
