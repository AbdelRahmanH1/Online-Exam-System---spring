package com.system.online_exam_system.exam.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "question")
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String questionText;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @ElementCollection
    @CollectionTable(
            name = "question_choices",
            joinColumns = @JoinColumn(name = "question_id")
    )
    @Column(name = "choice")
    private List<String> choices;

    @Column(name = "points")
    private double points;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public boolean hasChoices(){
        return  choices != null && !choices.isEmpty();
    }
    public boolean hasCorrectAnswer(List<String> proposedChoices){
        return proposedChoices.contains(correctAnswer);
    }
    public boolean isOwnedBy(Long userId) {
        return this.exam != null &&
                this.exam.getInstructor() != null &&
                this.exam.getInstructor().getId().equals(userId);
    }
}
