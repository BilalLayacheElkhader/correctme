package com.correctme.correctme.model.services.impl;

import com.correctme.correctme.model.domain.Task;
import com.correctme.correctme.model.domain.User;
import com.correctme.correctme.model.dto.TaskDTO;
import com.correctme.correctme.model.repository.mongo.TaskRepository;
import com.correctme.correctme.model.repository.jpa.UserRepository;
import com.correctme.correctme.model.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public TaskDTO newTask(TaskDTO taskDTO){

        Task task = mapTaskDTOToTask(taskDTO);

        task.setIdUserCorrector(assignRandomCorrector().getId());

        taskRepository.save(task);

        return new TaskDTO(task.getSprint(),task.getTasca(),task.getLevel(),task.getComments(),task.getIdUser(),task.getIdUserCorrector());
    }

    public User assignRandomCorrector(){
        List<User> availableUsers = userRepository.findByAvailableTrue();
        Random random = new Random();

        return Optional.ofNullable(availableUsers)
                .filter(users -> !users.isEmpty())
                .map(users -> {
                    User corrector = users.get(random.nextInt(users.size() - 1) + 1);
                    corrector.setAvailable(false);
                    return corrector;
                })
                .orElseThrow(() -> new RuntimeException("No available users found"));
    }

    private Task mapTaskDTOToTask(TaskDTO taskDTO) {
        Task newTask = Task.builder()
                .sprint(taskDTO.getSprint())
                .tasca(taskDTO.getTasca())
                .level(taskDTO.getLevel())
                .comments(taskDTO.getComments())
                .idUser(taskDTO.getIdUser())
                .build();
        return newTask;
    }
}
