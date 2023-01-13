package com.example.block7crudvalidation.security;

import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    IPersonaRepository personaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Persona usuario = personaRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario" + username + "no existe"));

        return new UserDetailsImpl(usuario);
    }
}
