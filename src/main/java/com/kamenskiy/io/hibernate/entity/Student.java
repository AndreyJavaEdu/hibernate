package com.kamenskiy.io.hibernate.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"course", "profile"})
@Builder
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private StudentProfile profile;

    public void addCourse(Course course) {
        this.course = course;
        course.getStudents().add(this);
    }
}
