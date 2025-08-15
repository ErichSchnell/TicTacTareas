package com.practicas.TicTacTareas.repository;

import com.practicas.TicTacTareas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//import java.lang.ScopedValue;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}