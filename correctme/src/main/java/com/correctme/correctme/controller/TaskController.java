package com.correctme.correctme.controller;

import com.correctme.correctme.model.dto.TaskDTO;
import com.correctme.correctme.model.services.TaskService;
import com.correctme.correctme.model.services.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private JwtService jwtService;


    public ResponseEntity<String> newTask (@PathVariable long idUser, @RequestBody TaskDTO taskDTO, @RequestHeader("Authorization") String authHeader){
        jwtService.checkId(idUser, authHeader);
        taskService.newTask(taskDTO);
        return ResponseEntity.ok("new task");
    }

}
