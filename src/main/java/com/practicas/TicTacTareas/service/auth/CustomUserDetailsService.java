package com.practicas.TicTacTareas.service.auth;

import com.practicas.TicTacTareas.entity.Usuario;
import com.practicas.TicTacTareas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                new ArrayList<>() // aquí podrían ir roles si los tuvieras
        );
    }

    public boolean userExists(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }

    public Usuario saveUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}