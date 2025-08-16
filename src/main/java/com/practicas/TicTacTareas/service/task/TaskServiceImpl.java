package com.practicas.TicTacTareas.service.task;

import com.practicas.TicTacTareas.dtos.task.TaskCreateDTO;
import com.practicas.TicTacTareas.dtos.task.TaskDTO;
import com.practicas.TicTacTareas.dtos.task.TaskPutDTO;
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
    public List<TaskDTO> getTasks(String email, String state, String title) {
        if (state != null && title != null) {
            return taskRepository.findByUsuarioEmailAndStateAndTitleContainingIgnoreCase(email, state, title).stream().map(TaskDTO::new).toList();
        } else if (state != null) {
            return taskRepository.findByUsuarioEmailAndState(email, state).stream().map(TaskDTO::new).toList();
        } else if (title != null) {
            return taskRepository.findByUsuarioEmailAndTitleContainingIgnoreCase(email, title).stream().map(TaskDTO::new).toList();
        } else {
            return taskRepository.findByUsuarioEmail(email).stream().map(TaskDTO::new).toList();
        }
    }
//mysql://root:EHzxdZCFeZtMbktmPADZpMAREYwKepdZ@yamabiko.proxy.rlwy.net:21389/railway
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

    @Override
    public TaskDTO taskPut(Long id, TaskPutDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task no encontrada"));

        // Actualizamos solo los campos que vienen en el DTO
        if(dto.getTitle() != null) task.setTitle(dto.getTitle());
        if(dto.getDescription() != null) task.setDescription(dto.getDescription());
        if(dto.getState() != null) task.setState(dto.getState());
        if(dto.getTimeout() != null) task.setTimeout(dto.getTimeout());

        Task updatedTask = taskRepository.save(task);
        return new TaskDTO(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}