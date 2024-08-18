package com.correctme.correctme.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TaskDTO {
    private int sprint;
    private int task;
    private int level;
    private String comments;
    private Long idUser;
    private Long idUserCorrector;
}
