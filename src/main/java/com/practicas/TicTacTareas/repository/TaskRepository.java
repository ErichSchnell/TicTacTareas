package com.practicas.TicTacTareas.repository;

import com.practicas.TicTacTareas.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // Buscar por state y title
    List<Task> findByUsuarioEmailAndStateAndTitleContainingIgnoreCase(String email, String state, String title);

    // Buscar solo por state
    List<Task> findByUsuarioEmailAndState(String email, String state);

    // Buscar solo por title
    List<Task> findByUsuarioEmailAndTitleContainingIgnoreCase(String email, String title);

    // Buscar solo por title
    List<Task> findByUsuarioEmail(String email);
}