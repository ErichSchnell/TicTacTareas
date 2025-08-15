package com.practicas.TicTacTareas.service;

import com.practicas.TicTacTareas.entity.Task;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Lazy
@Service
public class TaskServiceImpl implements TaskService{

    private final List<Task> tasks = new ArrayList<>(List.of(
            new Task(1, "Compras", "Comprar papas", "PENDIENTE", 123456L)
    ));

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

//    @Override
//    public void setTask(Task task) {
//        tasks.add(task);
//    }
}


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