package com.practicas.TicTacTareas.repository;

import com.practicas.TicTacTareas.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // Buscar por state y title
    List<Task> findByStateAndTitleContainingIgnoreCase(String state, String title);

    // Buscar solo por state
    List<Task> findByState(String state);

    // Buscar solo por title
    List<Task> findByTitleContainingIgnoreCase(String title);
}