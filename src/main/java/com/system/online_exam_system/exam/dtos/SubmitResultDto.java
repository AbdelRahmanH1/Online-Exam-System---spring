package com.system.online_exam_system.exam.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SubmitResultDto {
    double score;
    LocalDateTime submittedAt;
}
