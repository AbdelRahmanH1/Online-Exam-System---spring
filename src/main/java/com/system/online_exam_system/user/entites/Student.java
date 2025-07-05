package com.system.online_exam_system.user.entites;

import com.system.online_exam_system.exam.entites.ExamAttempt;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class Student extends User{
    private int grade;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ExamAttempt> attempts = new ArrayList<>();

    public boolean canPromoteTo(int newGrade){
        return newGrade >= this.grade;
    }
    public void promoteTo(int newGrade){
        this.grade = newGrade;
    }
}
