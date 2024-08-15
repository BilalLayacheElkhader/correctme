package com.correctme.correctme.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Task {
    private int sprint;
    private int task;
    private int level;
    private String comments;
    private Long idUser;
    private Long idUserCorrector;
}
