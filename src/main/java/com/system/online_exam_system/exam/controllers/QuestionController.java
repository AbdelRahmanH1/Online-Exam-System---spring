package com.system.online_exam_system.exam.controllers;

import com.system.online_exam_system.common.utils.ResponseUtil;
import com.system.online_exam_system.exam.dtos.AddQuestionRequest;
import com.system.online_exam_system.exam.services.QuestionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
@PreAuthorize("hasRole('INSTRUCTOR')")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/{examId}")
    public ResponseEntity<?> addQuestionToExam(@PathVariable Long examId, @Valid @RequestBody AddQuestionRequest request) {
        var response = questionService.addQuestion(examId,request);
        return ResponseUtil.success("Created Question Successfully", response, HttpStatus.CREATED);
    }
}
