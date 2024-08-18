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

        User corrector = assignRandomCorrector(task);
        task.setIdUserCorrector(corrector.getId());

        taskRepository.save(task);

        return new TaskDTO(task.getSprint(),task.getTask(),task.getLevel(),task.getComments(),task.getIdUser(),task.getIdUserCorrector());
    }
    public User assignRandomCorrector(Task task) {
        List<User> availableUsers = userRepository.findByAvailableTrue();

        if (availableUsers.isEmpty()) {
            throw new IllegalStateException("NO USER AVAILABLE");
        }
        Random random = new Random();
        User corrector = availableUsers.get(random.nextInt(availableUsers.size()));

        corrector.setAvailable(false);
        task.setIdUserCorrector(corrector.getId());

        userRepository.save(corrector);
        taskRepository.save(task);

        return corrector;
    }
    private Task mapTaskDTOToTask(TaskDTO taskDTO) {
        return new Task(
                taskDTO.getSprint(),
                taskDTO.getTask(),
                taskDTO.getLevel(),
                taskDTO.getComments(),
                taskDTO.getIdUser(),
                taskDTO.getIdUserCorrector()
        );
    }
}
