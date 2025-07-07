package com.system.online_exam_system.exam.services;

import com.system.online_exam_system.common.utils.SecurityUtil;
import com.system.online_exam_system.exam.dtos.AddQuestionRequest;
import com.system.online_exam_system.exam.dtos.QuestionResponse;
import com.system.online_exam_system.exam.exceptions.ExamNotFound;
import com.system.online_exam_system.exam.exceptions.ForbiddenException;
import com.system.online_exam_system.exam.exceptions.QuestionAlreadyExists;
import com.system.online_exam_system.exam.mappers.QuestionMapper;
import com.system.online_exam_system.exam.repositories.ExamRepository;
import com.system.online_exam_system.exam.repositories.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ExamRepository examRepository;
    private final QuestionMapper questionMapper;

    @Transactional
    public QuestionResponse addQuestion(Long examId, AddQuestionRequest request) {

        var exam = examRepository.findById(examId).orElseThrow(ExamNotFound::new) ;
        var userId = SecurityUtil.getUserId();

        if(!exam.getInstructor().getId().equals(userId)){
            throw new ForbiddenException("to add question");
        }

        if(questionRepository.existsByExamIdAndQuestionText(examId,request.getQuestion())){
            throw new QuestionAlreadyExists();
        }
        var question = questionMapper.toEntity(request);
        question.setExam(exam);

        var saved = questionRepository.save(question);
        return questionMapper.toResponse(saved);
    }
}
