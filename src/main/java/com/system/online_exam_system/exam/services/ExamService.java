package com.system.online_exam_system.exam.services;

import com.system.online_exam_system.common.utils.SecurityUtil;
import com.system.online_exam_system.exam.dtos.CreateExamRequest;
import com.system.online_exam_system.exam.dtos.ExamResponse;
import com.system.online_exam_system.exam.exceptions.InvalidTimeException;
import com.system.online_exam_system.exam.mappers.ExamMapper;
import com.system.online_exam_system.exam.repositories.ExamRepository;
import com.system.online_exam_system.user.entites.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@AllArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;
    private final EntityManager em;

    @Transactional
    public ExamResponse createExam(CreateExamRequest request) {

        var exam = examMapper.toExam(request);
        exam.setAllowMultipleAttempts(false);

        var instructorId = SecurityUtil.getUserId();
        var instructor = new Instructor();

        instructor.setId(instructorId);
        exam.setInstructor(instructor);

        if(!exam.isTimeValid()){
            throw new InvalidTimeException();
        }

        int duration = (int) Duration.between(exam.getStartTime(), exam.getEndTime()).toMinutes();
        if (duration < 15) {
            throw new InvalidTimeException();
        }
        exam.setDuration(duration);
        examRepository.save(exam);
        em.refresh(exam);
        return examMapper.toExamResponse(exam);
    }
}
