package com.practicas.TicTacTareas.service;

import com.practicas.TicTacTareas.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {

    public List<Task> getTasks();
    public void setTask(Task task);

}
