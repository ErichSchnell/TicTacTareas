package com.practicas.TicTacTareas.service;

import com.practicas.TicTacTareas.entity.Task;
import com.practicas.TicTacTareas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void setTask(Task task) {
        taskRepository.save(task);
    }
}



//    @Override
//    public void setTask(Task task) {
//        tasks.add(task);
//    }


/*
private final TaskRepositoryImp taskRepo = new TaskRepositoryImp();

    public List<Task> getTasks() {
        return taskRepo.getTasks();
    }

    public void setTask(Task task) {
        taskRepo.setTask(task);
    }

    public void editTask(Integer id, Task task) {
        for (Task t : taskRepo.getTasks()){
            if (t.getId().equals(id)){
                taskRepo.editTask(id, task);
            }
        }
    }

    public void deleteTask(Integer id) {
        for (Task t : taskRepo.getTasks()){
            if (t.getId().equals(id)){
                taskRepo.deleteTask(id);
            }
        }
    }
 */