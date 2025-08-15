package com.practicas.TicTacTareas.service;

import com.practicas.TicTacTareas.DTOs.task.TaskCreateDTO;
import com.practicas.TicTacTareas.DTOs.task.TaskDTO;
import com.practicas.TicTacTareas.entity.Task;
import com.practicas.TicTacTareas.entity.Usuario;
import com.practicas.TicTacTareas.repository.TaskRepository;
import com.practicas.TicTacTareas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<TaskDTO> getTasks() {
        return taskRepository.findAll().stream().map(TaskDTO::new).toList();
    }

    @Override
    public TaskDTO setTask(TaskCreateDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setState(dto.getState());
        task.setTimeout(dto.getTimeout());

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        task.setUsuario(usuario);

        Task taskResponse = taskRepository.save(task);
        return new TaskDTO(taskResponse);
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