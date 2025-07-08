package com.system.online_exam_system.exam.services;

import com.system.online_exam_system.common.exceptions.ApiException;
import com.system.online_exam_system.common.exceptions.UserNotFound;
import com.system.online_exam_system.common.utils.SecurityUtil;
import com.system.online_exam_system.exam.dtos.SubmitExamAttemptRequest;
import com.system.online_exam_system.exam.dtos.SubmitResultDto;
import com.system.online_exam_system.exam.entites.ExamAttempt;
import com.system.online_exam_system.exam.entites.StudentAnswer;
import com.system.online_exam_system.exam.exceptions.*;
import com.system.online_exam_system.exam.repositories.AttemptRepository;
import com.system.online_exam_system.exam.repositories.ExamRepository;
import com.system.online_exam_system.exam.repositories.QuestionRepository;
import com.system.online_exam_system.exam.repositories.StudentAnswerRepository;
import com.system.online_exam_system.user.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExamAttemptService {

    private final ExamRepository examRepository;
    private final AttemptRepository attemptRepository;
    private final StudentRepository studentRepository;
    private final QuestionRepository questionRepository;
    private final StudentAnswerRepository studentAnswerRepository;

    public Long startExam(Long examId){

        Long studentId = SecurityUtil.getUserId();

        // 1 check exam
        var exam = examRepository.findById(examId).orElseThrow(ExamNotFound::new);

        // check the time of attempts
        LocalDateTime now = LocalDateTime.now();
        if(now.isBefore(exam.getStartTime())){
            throw new ExamNotStartedYetException();
        }
        if(now.isAfter(exam.getEndTime())){
            throw new ExamTimeFinishedException();
        }
        // check student already Attempt
        if(!exam.isAllowMultipleAttempts() && attemptRepository.existsByStudentIdAndExamId(studentId, examId)){
            throw new ApiException("You already attempted this exam.", HttpStatus.BAD_REQUEST);
        }

        // check User grade
        var student = studentRepository.findById(studentId).orElseThrow(UserNotFound::new);

        if(student.getGrade() != exam.getGradeLevel()){
            throw new GradeMismatchException();
        }

        ExamAttempt examAttempt = new ExamAttempt();
        examAttempt.setExam(exam);
        examAttempt.setStudent(student);
        examAttempt.setStartedAt(LocalDateTime.now());
        examAttempt.setScore(0.0);
        attemptRepository.save(examAttempt);
        return examAttempt.getId();
    }

    public SubmitResultDto submitExamAttempt(Long examAttemptId, SubmitExamAttemptRequest request){
        Long studentId = SecurityUtil.getUserId();

        //1 - fetch attempt with exam & student
        var attempt = attemptRepository.findByIdWithExamAndStudent(examAttemptId, studentId).orElseThrow(UserNotFound::new);

        //2- validate ownership
        if(!attempt.getStudent().getId().equals(studentId)){
            throw new ForbiddenException("submit someone else’s attempt.");
        }

        //3- check double submit
        if(attempt.getSubmittedAt()!=null){
            throw new ApiException("the exam has already been submitted.", HttpStatus.BAD_REQUEST);
        }
        //4- check time expired
        var deadline = attempt.getExam().getEndTime();
        if (LocalDateTime.now().isAfter(deadline)) {
            throw new ExamTimeFinishedException();
        }
        //5- grade
        double totalScore = 0;
        List<StudentAnswer> savedAnswer = new ArrayList<>();

        for(var dto: request.getAnswers()){
            var question = questionRepository.findById(dto.getQuestionId()).orElseThrow(UserNotFound::new);
            StudentAnswer answer = new StudentAnswer();
            answer.setQuestion(question);
            answer.setExamAttempt(attempt);
            answer.setAnswer(dto.getAnswer());

            if(question.isCorrectAnswer(dto.getAnswer())){
                answer.setScore(question.getPoints());
                totalScore+=question.getPoints();
            }else{
                answer.setScore(0.0);
            }

            savedAnswer.add(answer);
        }
        studentAnswerRepository.saveAll(savedAnswer);

        // finalize attempt
        attempt.setSubmittedAt(LocalDateTime.now());
        attempt.setScore(totalScore);
        attemptRepository.save(attempt);

        return new SubmitResultDto(totalScore,attempt.getSubmittedAt());
    }
}
