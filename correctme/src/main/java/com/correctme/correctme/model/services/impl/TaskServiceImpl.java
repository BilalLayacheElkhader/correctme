package com.correctme.correctme.model.services.impl;

import com.correctme.correctme.model.domain.Task;
import com.correctme.correctme.model.dto.TaskDTO;
import com.correctme.correctme.model.repository.TaskRepository;
import com.correctme.correctme.model.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public Task newTask(TaskDTO taskDTO){
        //Task task = llamar a mapper enviandole taskDTO
        //metodo que busca y asigna userCorrector y hacer setter
        //taskRepository.save(task);
        return null;
    }
}
