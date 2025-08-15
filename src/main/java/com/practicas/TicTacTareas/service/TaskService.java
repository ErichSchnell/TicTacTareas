package com.practicas.TicTacTareas.service;

import com.practicas.TicTacTareas.DTOs.task.TaskCreateDTO;
import com.practicas.TicTacTareas.DTOs.task.TaskDTO;
import com.practicas.TicTacTareas.entity.Task;

import java.util.List;

public interface TaskService {

    public List<TaskDTO> getTasks();
    public TaskDTO setTask(TaskCreateDTO dto);

}
