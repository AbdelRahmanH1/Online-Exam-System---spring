package com.system.online_exam_system.exam.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class AddChoicesRequest {
    @NotNull
    @Size(min = 2, message = "At least two choices required")
    private List<@NotBlank String> choices;
}
