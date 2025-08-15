package com.practicas.TicTacTareas.service.task;

import com.practicas.TicTacTareas.dtos.task.TaskCreateDTO;
import com.practicas.TicTacTareas.dtos.task.TaskDTO;
import com.practicas.TicTacTareas.dtos.task.TaskPutDTO;

import java.util.List;

public interface TaskService {

    public List<TaskDTO> getTasks();
    public TaskDTO setTask(TaskCreateDTO dto);
    public TaskDTO taskPut(Long id, TaskPutDTO dto);
    public void deleteTask(Long id);

}
