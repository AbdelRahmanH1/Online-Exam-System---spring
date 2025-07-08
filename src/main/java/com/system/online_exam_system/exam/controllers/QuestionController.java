package com.system.online_exam_system.exam.controllers;

import com.system.online_exam_system.common.utils.ResponseUtil;
import com.system.online_exam_system.exam.dtos.AddChoicesRequest;
import com.system.online_exam_system.exam.dtos.AddQuestionRequest;
import com.system.online_exam_system.exam.dtos.UpdateQuestionRequest;
import com.system.online_exam_system.exam.services.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Questions", description = "Endpoints to manage questions and choices for exams")
@SecurityRequirement(name = "bearerAuth")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/{examId}")
    @Operation(summary = "Add a question to an exam")
    public ResponseEntity<?> addQuestionToExam(@PathVariable Long examId, @Valid @RequestBody AddQuestionRequest request) {
        var response = questionService.addQuestion(examId,request);
        return ResponseUtil.success("Created Question Successfully", response, HttpStatus.CREATED);
    }

    @PostMapping("/{questionId}/choices")
    @Operation(summary = "Add multiple choices to a question")
    public ResponseEntity<?> addChoicesToQuestion(@PathVariable Long questionId, @Valid @RequestBody AddChoicesRequest request) {
        questionService.addChoices(questionId,request);
        return ResponseUtil.success("Add Choices successfully",null, HttpStatus.CREATED);
    }

    @PatchMapping("/{questionId}")
    @Operation(summary = "Update an existing question")
    public ResponseEntity<?> updateQuestion(@PathVariable Long questionId,@Valid @RequestBody UpdateQuestionRequest request) {
       var response =  questionService.updateQuestion(questionId,request);
       return ResponseUtil.success("Updated Question Successfully", response, HttpStatus.OK);
    }

    @GetMapping("/{examId}")
    @Operation(summary = "Get all questions for an exam")
    public ResponseEntity<?> getQuestionByExamId(@PathVariable Long examId) {
        var response = questionService.getQuestionsByExam(examId);
        return ResponseUtil.success("Get Exam Question Successfully", response);
    }

    @DeleteMapping("/{questionId}")
    @Operation(summary = "Delete a question by ID")
    public ResponseEntity<?> deleteQuestionById(@PathVariable Long questionId) {
        questionService.deleteQuestionById(questionId);
        return ResponseUtil.success("Deleted Question Successfully", null);
    }
}
