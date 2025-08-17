package com.practicas.TicTacTareas.controller;

import com.practicas.TicTacTareas.dtos.task.TaskCreateDTO;
import com.practicas.TicTacTareas.dtos.task.TaskDTO;
import com.practicas.TicTacTareas.dtos.task.TaskPutDTO;
import com.practicas.TicTacTareas.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<List<TaskDTO>> getTask(
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String title,
            Authentication authentication
    ) {
        String email = authentication.getName();

        return ResponseEntity.ok(taskService.getTasks(email, state, title));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> postTask(
            @RequestBody TaskCreateDTO taskCreateDTO,
            Authentication authentication
    ) {
        String email = authentication.getName();
        TaskDTO taskResponse = taskService.setTask(email, taskCreateDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(taskResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putTask(@PathVariable Long id, @RequestBody TaskPutDTO taskPutDto) {
        try {
            TaskDTO updatedTask = taskService.taskPut(id, taskPutDto);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // o podés crear un DTO de error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
