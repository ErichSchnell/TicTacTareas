package com.practicas.TicTacTareas.controller;

import com.practicas.TicTacTareas.DTOs.task.TaskCreateDTO;
import com.practicas.TicTacTareas.DTOs.task.TaskDTO;
import com.practicas.TicTacTareas.entity.Task;
import com.practicas.TicTacTareas.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTask() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @PostMapping
    public ResponseEntity<TaskDTO> postTask(@RequestBody TaskCreateDTO taskCreateDTO) {
        TaskDTO taskResponse = taskService.setTask(taskCreateDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(taskResponse);
    }
}


//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> putTask(@PathVariable Integer id, @RequestBody Task task) {
//        taskService.editTask(id,task);
//        return ResponseEntity.ok(task);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
//        taskService.deleteTask(id);
//        return ResponseEntity.noContent().build();
//    }
