package com.practicas.TicTacTareas.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practicas.TicTacTareas.entity.Task;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class TaskServiceJSONImpl implements TaskService {

    @Override
    public List<Task> getTasks() {
        List<Task> tasks;

        try {
            tasks = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/task.json"),
                    new TypeReference<List<Task>>() {});

            return tasks;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void setTask(Task task) {
//        tasks.add(task);
//    }
}