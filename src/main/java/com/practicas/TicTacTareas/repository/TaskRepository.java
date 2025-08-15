package com.practicas.TicTacTareas.repository;

import com.practicas.TicTacTareas.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TaskRepository extends JpaRepository<Task, Long> { }