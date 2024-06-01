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
@Table(name = "users", schema = "public")
public class User {

    private String username;
    @EmbeddedId
    private PersonalInfo personalInfo;

    @Enumerated(EnumType.STRING)
    private Role role;
}
