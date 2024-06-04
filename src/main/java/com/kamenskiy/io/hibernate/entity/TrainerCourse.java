package com.kamenskiy.io.hibernate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "trainers_cources")
public class TrainerCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public void setCourse(Course course) {
        this.course = course;
        course.getTrainerCourses().add(this);
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
        trainer.getTrainerCourses().add(this);
    }
}
