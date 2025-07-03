package com.system.online_exam_system.user.repositories;

import com.system.online_exam_system.user.entites.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Page<Student> findByGrade(int grade, Pageable pageable);

    Page<Student> findByNameContainingIgnoreCase(String name,Pageable pageable);

    Page<Student> findByNameContainingIgnoreCaseAndGrade(String name, int grade,Pageable pageable);
}
