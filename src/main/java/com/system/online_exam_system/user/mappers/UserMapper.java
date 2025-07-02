package com.system.online_exam_system.user.mappers;

import com.system.online_exam_system.user.dtos.CreateUserRequest;
import com.system.online_exam_system.user.dtos.UserResponse;
import com.system.online_exam_system.user.entites.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
    User toEntity(CreateUserRequest request);
}
