package com.correctme.correctme.model.domain;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Task {
    private int sprint;
    private int task;
    private int level;
    private String comments;
    private Long idUser;
    private Long idUserCorrector;
}
