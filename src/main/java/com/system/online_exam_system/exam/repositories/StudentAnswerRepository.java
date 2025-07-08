package com.system.online_exam_system.exam.repositories;

import com.system.online_exam_system.exam.entites.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {
}
