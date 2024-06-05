package com.kamenskiy.io.hibernate.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Manager extends User {
    private String project;

    @Builder
    public Manager(Long id, Profile profile, String username, PersonalInfo personalInfo, Role role, Company company, List<UserChat> userChats, String project) {
        super(id, profile, username, personalInfo, role, company, userChats);
        this.project = project;
    }
}
