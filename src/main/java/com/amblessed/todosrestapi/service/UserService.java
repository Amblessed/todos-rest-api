package com.amblessed.todosrestapi.service;



/*
 * @Project Name: todos-rest-api
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17-Sep-25
 */


import com.amblessed.todosrestapi.request.UserResponse;

public interface UserService {

    UserResponse getUserInfo();
    void deleteUser();
}
