package com.system.online_exam_system.exam.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PastExamDto {
    Long examId;
    String examTitle;
    double score;
    LocalDateTime submittedAt;
}
