package com.correctme.correctme.model.services;

import com.correctme.correctme.model.domain.Task;
import com.correctme.correctme.model.dto.TaskDTO;

public interface TaskService {
    TaskDTO newTask(TaskDTO taskDTO);
}
