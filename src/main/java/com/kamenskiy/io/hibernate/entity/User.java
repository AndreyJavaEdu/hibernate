package com.kamenskiy.io.hibernate.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "company")
@EqualsAndHashCode(of = "username")
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Column(unique = true, nullable = false)
    private String username;
    @Embedded
    private PersonalInfo personalInfo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

}
