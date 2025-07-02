package com.system.online_exam_system.user.services;

import com.system.online_exam_system.user.dtos.CreateUserRequest;
import com.system.online_exam_system.user.dtos.UserResponse;
import com.system.online_exam_system.user.entites.Instructor;
import com.system.online_exam_system.user.entites.Student;
import com.system.online_exam_system.user.entites.User;
import com.system.online_exam_system.user.enums.Role;
import com.system.online_exam_system.user.mappers.InstructorMapper;
import com.system.online_exam_system.user.mappers.StudentMapper;
import com.system.online_exam_system.user.mappers.UserMapper;
import com.system.online_exam_system.user.repositories.InstructorRepository;
import com.system.online_exam_system.user.repositories.StudentRepository;
import com.system.online_exam_system.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminUserService {
    public final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final UserMapper userMapper;
    private final StudentMapper studentMapper;
    private final InstructorMapper instructorMapper;


    // createUser
    @Transactional
    public UserResponse createUser(@Valid CreateUserRequest request) {

        if (userRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Username already exists");
        }
        Role role = request.getRole();

        if (role == Role.STUDENT && request.getGrade() == null) {
            throw new IllegalArgumentException("Grade is required for students.");
        }

        if (role == Role.INSTRUCTOR && request.getDepartment() == null) {
            throw new IllegalArgumentException("Department  is required for instructors.");
        }

        User user;


        switch (role){
            case STUDENT ->{
                Student student = studentMapper.toStudent(request);
                user = studentRepository.save(student);
            }
            case INSTRUCTOR ->{
                Instructor instructor = instructorMapper.toInstructor(request);
                user = instructorRepository.save(instructor);
            }
            case ADMIN ->{
                user = userMapper.toEntity(request);
                userRepository.save(user);
            }
            default -> throw new IllegalArgumentException("Invalid role");
        }
        return userMapper.toUserResponse(user);

    }


    // getAllUser
    // deleteUser

}
