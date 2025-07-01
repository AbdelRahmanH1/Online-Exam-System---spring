package com.system.online_exam_system.user.repositories;

import com.system.online_exam_system.user.entites.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {
}
