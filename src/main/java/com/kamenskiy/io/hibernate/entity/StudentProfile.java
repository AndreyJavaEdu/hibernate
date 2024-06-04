package com.kamenskiy.io.hibernate.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"student"})
@Builder
@Entity
@Table(name = "student_profile")
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = false, name = "average_score")
    private Short averageScore;

    public void setStudent(Student student) {
        this.student = student;
        student.setProfile(this);
    }
}
