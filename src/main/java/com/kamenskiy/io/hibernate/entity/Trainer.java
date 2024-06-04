package com.kamenskiy.io.hibernate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Builder.Default
    @OneToMany(mappedBy = "trainer")
    private List<TrainerCourse> trainerCourses = new ArrayList<>();

}
