package com.system.online_exam_system.exam.entites;

import com.system.online_exam_system.user.entites.Instructor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "exam")
@Getter
@Setter
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "duration_minutes")
    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private Instructor instructor;

    @Column(name = "grade_level")
    private int gradeLevel;

    @Column(name = "allow_multiple_attempts")
    private boolean allowMultipleAttempts;
}
