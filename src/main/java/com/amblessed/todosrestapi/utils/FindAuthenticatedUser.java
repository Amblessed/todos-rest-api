package com.amblessed.todosrestapi.utils;



/*
 * @Project Name: todos-rest-api
 * @Author: Okechukwu Bright Onwumere
 * @Created: 18-Sep-25
 */


import com.amblessed.todosrestapi.entity.User;

public interface FindAuthenticatedUser {

    User getAuthenticatedUser();
}
