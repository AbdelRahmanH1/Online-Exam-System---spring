package com.system.online_exam_system.user.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateGradeRequest {

    @NotNull
    @Min(1)
    @Max(12)
    private int grade;
}
