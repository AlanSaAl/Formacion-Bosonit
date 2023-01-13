package com.example.block7crudvalidation.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SimpleGrantedAuthority implements GrantedAuthority {
    private final String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
