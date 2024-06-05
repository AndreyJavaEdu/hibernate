package com.kamenskiy.io.hibernate.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Programmer extends User {
    @Enumerated(EnumType.STRING)
    private Language language;

    @Builder
    public Programmer(Long id, Profile profile, String username, PersonalInfo personalInfo, Role role, Company company, List<UserChat> userChats, Language language) {
        super(id, profile, username, personalInfo, role, company, userChats);
        this.language = language;
    }
}
