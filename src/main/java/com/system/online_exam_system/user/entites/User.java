package com.system.online_exam_system.user.entites;

import com.system.online_exam_system.user.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "created_at", updatable = false,insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false,insertable = false)
    private LocalDateTime updatedAt;

    public boolean hasRole(Role role) {
        return role == this.role;
    }
}
