package com.kamenskiy.io.hibernate.starter;
import com.kamenskiy.io.hibernate.entity.User;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
//        configuration.addAnnotatedClass(User.class);
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());

        try (var sesssionFactory = configuration.buildSessionFactory();
             var session = sesssionFactory.openSession();) {

            session.beginTransaction();

            session.persist(User.builder()
                            .username("kamenskiy@gmail.com")
                            .firstname("Andrey")
                            .lastname("Kamenskiy")
                            .birthDate(LocalDate.of(1990, 1, 23))
                            .age(34)
                    .build());
            session.getTransaction().commit();
        }
    }
}
