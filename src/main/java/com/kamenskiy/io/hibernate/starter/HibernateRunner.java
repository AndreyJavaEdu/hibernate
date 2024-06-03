package com.kamenskiy.io.hibernate.starter;

import com.kamenskiy.io.hibernate.entity.*;
import com.kamenskiy.io.hibernate.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
@Slf4j
public class HibernateRunner {
    public static void main(String[] args) {
        var company = Company.builder()
                .name("Mail")
                .build();

        //TRANSIENT
        var user = User.builder()
                .username("kamenskiy212@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Alex2")
                        .lastname("Kamenskiy")
                        .birthday(new Birthday(LocalDate.of(1990, 2, 12)))
                        .build())

                .role(Role.USER)
                .company(company)
                .build();
        log.info("User object in transient state: {}", user);
        User user2 = null;
        //TRANSIENT
        try (var sessionFactory = HibernateUtil.buildSessionFactory();) {
            try (var session1 = sessionFactory.openSession()) {

                session1.beginTransaction();
                session1.saveOrUpdate(user);

                session1.getTransaction().commit();
            }
        }catch (Exception e) {
            log.error("Exception occured: ", e);
            throw e;
        }

    }
}
