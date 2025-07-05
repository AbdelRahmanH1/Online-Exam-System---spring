package com.system.online_exam_system.user.repositories;

import com.system.online_exam_system.user.entites.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    Page<Instructor> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Instructor> findByDepartmentContainingIgnoreCase(String department,Pageable pageable);

    Page<Instructor> findByNameContainingIgnoreCaseAndDepartmentContainingIgnoreCase(String name, String department,Pageable pageable);
}
