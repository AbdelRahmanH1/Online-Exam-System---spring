package com.system.online_exam_system.user.entites;

import com.system.online_exam_system.exam.entites.Exam;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class Instructor extends User{

    @Column(name = "department")
    private String department;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<Exam> createdExams = new ArrayList<>();

}
