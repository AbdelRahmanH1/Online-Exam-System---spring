package com.system.online_exam_system.common.utils;

import com.system.online_exam_system.auth.entities.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtil {

    public static Long getUserId() {
        var user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
}
