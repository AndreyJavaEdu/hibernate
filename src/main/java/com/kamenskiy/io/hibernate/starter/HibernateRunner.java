package com.kamenskiy.io.hibernate.starter;

import com.kamenskiy.io.hibernate.converter.BirthdayConverter;
import com.kamenskiy.io.hibernate.entity.Birthday;
import com.kamenskiy.io.hibernate.entity.Role;
import com.kamenskiy.io.hibernate.entity.User;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAttributeConverter(BirthdayConverter.class, true);
//        configuration.addAnnotatedClass(User.class);
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());

        try (var sesssionFactory = configuration.buildSessionFactory();
             var session = sesssionFactory.openSession();) {

            session.beginTransaction();

            session.persist(User.builder()
                    .username("kamenskiy2@gmail.com")
                    .firstname("Andrey")
                    .lastname("Kamenskiy")
                    .birthday(new Birthday(LocalDate.of(1990, 2, 12)))
                    .role(Role.USER)
                    .build());
            session.getTransaction().commit();
        }
    }
}
