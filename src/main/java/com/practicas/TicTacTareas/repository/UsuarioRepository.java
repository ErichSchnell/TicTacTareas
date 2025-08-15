package com.practicas.TicTacTareas.repository;

import com.practicas.TicTacTareas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> { }