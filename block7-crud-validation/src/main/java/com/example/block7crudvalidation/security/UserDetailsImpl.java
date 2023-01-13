package com.example.block7crudvalidation.security;


import com.example.block7crudvalidation.domain.Persona;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final Persona persona;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (persona.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("Administrador"));
        } else {
            authorities.add(new SimpleGrantedAuthority("Usuario"));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return persona.getPassword();
    }

    @Override
    public String getUsername() {
        return persona.getUsuario();
    }

    public String getNombre() {return  persona.getName();}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
