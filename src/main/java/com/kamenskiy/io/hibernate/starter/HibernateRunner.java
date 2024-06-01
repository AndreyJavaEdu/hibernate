package com.kamenskiy.io.hibernate.starter;

import com.kamenskiy.io.hibernate.entity.Birthday;
import com.kamenskiy.io.hibernate.entity.Role;
import com.kamenskiy.io.hibernate.entity.User;
import com.kamenskiy.io.hibernate.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
@Slf4j
public class HibernateRunner {
    public static void main(String[] args) {
        //TRANSIENT
        var user = User.builder()
                .username("kamenskiy2@gmail.com")
                .firstname("Andrey")
                .lastname("Kamenskiy")
                .birthday(new Birthday(LocalDate.of(1990, 2, 12)))
                .role(Role.USER)
                .build();
        log.info("User object in transient state: {}", user);

        //TRANSIENT
        try (var sesssionFactory = HibernateUtil.buildSessionFactory();) {
            try (var session1 = sesssionFactory.openSession()) {

                session1.beginTransaction();
                //user PERSISTENT к сессии 1
                session1.saveOrUpdate(user);
                user.setFirstname("Olga");
                log.warn("User firstname was changed: {}", user);
                System.out.println(session1.isDirty());
                log.debug("User: {}, session: {}", user, session1);
                session1.getTransaction().commit();
            }
        }catch (Exception e) {
            log.error("Exception occured: ", e);
            throw e;
        }
    }
}
