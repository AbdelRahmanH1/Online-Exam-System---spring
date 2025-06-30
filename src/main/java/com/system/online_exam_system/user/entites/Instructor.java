package com.system.online_exam_system.user.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "instructor")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class Instructor extends User{
}
