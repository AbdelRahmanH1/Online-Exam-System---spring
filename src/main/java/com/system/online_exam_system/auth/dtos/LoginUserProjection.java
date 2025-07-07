package com.system.online_exam_system.auth.dtos;

import com.system.online_exam_system.user.enums.Role;

public interface LoginUserProjection {
    Long getId();
    String getUsername();
    String getPassword();
    Role getRole();
}
