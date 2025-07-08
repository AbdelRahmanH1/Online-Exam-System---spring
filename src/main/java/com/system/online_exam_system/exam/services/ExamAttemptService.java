package com.system.online_exam_system.exam.services;

import com.system.online_exam_system.common.exceptions.ApiException;
import com.system.online_exam_system.common.exceptions.UserNotFound;
import com.system.online_exam_system.common.utils.SecurityUtil;
import com.system.online_exam_system.exam.entites.ExamAttempt;
import com.system.online_exam_system.exam.exceptions.*;
import com.system.online_exam_system.exam.repositories.AttemptRepository;
import com.system.online_exam_system.exam.repositories.ExamRepository;
import com.system.online_exam_system.user.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ExamAttemptService {

    private final ExamRepository examRepository;
    private final AttemptRepository attemptRepository;
    private final StudentRepository studentRepository;

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

        attemptRepository.save(examAttempt);
        return examAttempt.getId();
    }
}
