package com.system.online_exam_system.user.services;

import com.system.online_exam_system.common.exceptions.ApiException;
import com.system.online_exam_system.common.exceptions.UserNotFound;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@AllArgsConstructor
public class AdminUserService {
    public final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final UserMapper userMapper;
    private final StudentMapper studentMapper;
    private final InstructorMapper instructorMapper;


    private String generateUsername(String name){
        name = name.toLowerCase();
        String[] parts = name.split("\\s+");
        if(parts.length<2){
            throw new ApiException("Invalid name format", HttpStatus.BAD_REQUEST);
        }
        String firstName = parts[0];
        String middleName = parts[1].substring(0, 1).toUpperCase() ;
        String lastName = (parts.length >=3) ? parts[2] : parts[1];

        String base = firstName + "_"+middleName + "_"+lastName;
        String username;
        int attempts = 0;

        do{
            int random = 1000 + new Random().nextInt(9000);
            username = base+"_"+random;
            attempts++;
            if (attempts > 5) {
                throw new ApiException("Unable to generate unique username", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }while (userRepository.existsByUsername(username));
        return username;
    }

    @Transactional
    public UserResponse createUser(@Valid CreateUserRequest request) {
        request.validateNameFormat();
        Role role = request.getRole();

        if (role == Role.STUDENT && request.getGrade() == null) {
            throw new ApiException("Grade is required for students.",HttpStatus.BAD_REQUEST);
        }

        if (role == Role.INSTRUCTOR && request.getDepartment() == null) {
            throw new ApiException("Department  is required for instructors.",HttpStatus.BAD_REQUEST);
        }

        User user;
        String username = generateUsername(request.getName());
        switch (role) {
            case STUDENT -> {
                Student student = studentMapper.toStudent(request);
                student.setUsername(username);
                user = studentRepository.save(student);
            }
            case INSTRUCTOR -> {
                Instructor instructor = instructorMapper.toInstructor(request);
                instructor.setUsername(username);
                user = instructorRepository.save(instructor);
            }
            case ADMIN -> {
                user = userMapper.toEntity(request);
                user.setUsername(username);
                userRepository.save(user);
            }
            default -> throw new IllegalArgumentException("Invalid role");
        }
        return userMapper.toUserResponse(user);

    }


    public Page<UserResponse> getAllUsers(Integer pageNumber) {
        int page = (pageNumber == null || pageNumber < 0) ? 0 : pageNumber;
        Pageable pageable = PageRequest.of(page, 10);
        return userRepository.findAllUsers(pageable);
    }

    public UserResponse getUserById(Long id) {
        return userRepository.findUserResponseById(id).orElseThrow(UserNotFound::new);
    }

    public void deleteUserById(Long id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFound();
        }
        userRepository.deleteById(id);
    }

}
