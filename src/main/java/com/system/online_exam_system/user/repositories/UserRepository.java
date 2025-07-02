package com.system.online_exam_system.user.repositories;

import com.system.online_exam_system.user.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByName(String name);
}
