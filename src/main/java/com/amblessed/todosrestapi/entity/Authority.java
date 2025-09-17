package com.amblessed.todosrestapi.entity;



/*
 * @Project Name: todos-rest-api
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17-Sep-25
 */

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Embeddable  // This indicates that this class does not have its own identity and is intended to be embedded inside another entity.
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Authority implements GrantedAuthority {

    @Column(name = "authority_name", nullable = false)
    private String authorityName;

    @Override
    public String getAuthority() {
        return authorityName;
    }
}
