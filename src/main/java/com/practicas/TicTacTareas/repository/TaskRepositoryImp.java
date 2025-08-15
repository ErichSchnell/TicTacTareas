//package com.practicas.TicTacTareas.repository;
//
//import com.practicas.TicTacTareas.entity.Task;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Repository
//public class TaskRepositoryImp {
//
//    private final List<Task> tasks = new ArrayList<>(Arrays.asList(
//            new Task(1,"Compras", "Comprar papas", "PENDIENTE", 123456)
//    ));
//
//    public List<Task> getTasks() {
//        return tasks;
//    }
//
//    public void setTask(Task task) {
//        this.tasks.add(task);
//    }
//
//    public void editTask(Integer id, Task task) {
//        Task newTaks = tasks.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
//
//        newTaks.setTitle(task.getTitle());
//        newTaks.setDescription(task.getDescription());
//        newTaks.setState(task.getState());
//        newTaks.setTimeout(task.getTimeout());
//    }
//
//    public void deleteTask(Integer id) {
//        tasks.removeIf(t -> t.getId().equals(id));
//    }
//}
