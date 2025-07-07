package com.system.online_exam_system.exam.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateExamRequest {
    @NotBlank(message = "Title is required")
    private String title;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @NotNull(message = "End time is required")
    private LocalDateTime endTime;

    @Min(value = 1, message = "Grade level must be at least 1")
    @Max(value = 12)
    private int grade_level;

}
