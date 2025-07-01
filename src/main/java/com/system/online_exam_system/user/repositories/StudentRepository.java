package com.system.online_exam_system.user.repositories;

import com.system.online_exam_system.user.entites.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
