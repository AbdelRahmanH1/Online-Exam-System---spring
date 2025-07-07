package com.system.online_exam_system.exam.services;

import com.system.online_exam_system.common.exceptions.ApiException;
import com.system.online_exam_system.common.utils.SecurityUtil;
import com.system.online_exam_system.exam.dtos.AddChoicesRequest;
import com.system.online_exam_system.exam.dtos.AddQuestionRequest;
import com.system.online_exam_system.exam.dtos.QuestionResponse;
import com.system.online_exam_system.exam.dtos.UpdateQuestionRequest;
import com.system.online_exam_system.exam.entites.Question;
import com.system.online_exam_system.exam.exceptions.ExamNotFound;
import com.system.online_exam_system.exam.exceptions.ForbiddenException;
import com.system.online_exam_system.exam.exceptions.QuestionAlreadyExists;
import com.system.online_exam_system.exam.exceptions.QuestionNotFound;
import com.system.online_exam_system.exam.mappers.QuestionMapper;
import com.system.online_exam_system.exam.repositories.ExamRepository;
import com.system.online_exam_system.exam.repositories.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ExamRepository examRepository;
    private final QuestionMapper questionMapper;

    @Transactional
    public QuestionResponse addQuestion(Long examId, AddQuestionRequest request) {

        var exam = examRepository.findById(examId).orElseThrow(ExamNotFound::new);
        var userId = SecurityUtil.getUserId();

        if (!exam.getInstructor().getId().equals(userId)) {
            throw new ForbiddenException("to add question");
        }

        if (questionRepository.existsByExamIdAndQuestionText(examId, request.getQuestion())) {
            throw new QuestionAlreadyExists();
        }
        var question = questionMapper.toEntity(request);
        question.setExam(exam);

        var saved = questionRepository.save(question);
        return questionMapper.toResponse(saved);
    }

    @Transactional
    public void addChoices(Long questionId, AddChoicesRequest request) {

        var question = questionRepository.findById(questionId).orElseThrow(QuestionNotFound::new);

        var userId = SecurityUtil.getUserId();
        if (!question.isOwnedBy(userId)) {
            throw new ForbiddenException("to add question");
        }
        validateQuestion(request.getChoices(), question);
        question.setChoices(request.getChoices());
        questionRepository.save(question);
    }

    private void validateQuestion(List<String> choices, Question question) {
        if (question.hasChoices()) {
            throw new ApiException("Choices already exist for this question", HttpStatus.BAD_REQUEST);
        }
        if (choices == null || choices.size() < 2) {
            throw new ApiException("Choices should be at least 2 choices.", HttpStatus.BAD_REQUEST);
        }
        if (!question.hasCorrectAnswer(choices)) {
            throw new ApiException("Correct answer must be included in the choices.", HttpStatus.BAD_REQUEST);
        }

    }

    public QuestionResponse updateQuestion(Long questionId, UpdateQuestionRequest request) {
        var question = questionRepository.findById(questionId).orElseThrow(QuestionNotFound::new);

        var userId = SecurityUtil.getUserId();
        if (!question.isOwnedBy(userId)) {
            throw new ForbiddenException("to update question");
        }
        questionMapper.updateQuestion(request, question);
        if (request.getAnswer() != null &&
                question.hasChoices() &&
                !question.getChoices().contains(request.getAnswer())) {
            throw new ApiException("Correct answer must be one of the existing choices.", HttpStatus.BAD_REQUEST);
        }

        questionRepository.save(question);
        return questionMapper.toResponse(question);
    }
}
