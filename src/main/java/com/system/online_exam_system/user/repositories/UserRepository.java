package com.system.online_exam_system.user.repositories;

import com.system.online_exam_system.user.dtos.UserResponse;
import com.system.online_exam_system.user.entites.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByName(String name);

    boolean existsById(Long id);

    @Query("SELECT u.id , u.name,u.role FROM User u")
    Page<UserResponse> findAllUsers(Pageable pageable);

    @Query("SELECT u.id , u.name,u.role FROM User u WHERE u.id = :id")
    Optional<UserResponse> findUserResponseById(@Param("id") Long id);

    boolean existsByUsername(String username);
}
