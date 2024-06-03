package com.kamenskiy.io.hibernate.starter;

import com.kamenskiy.io.hibernate.entity.*;
import com.kamenskiy.io.hibernate.util.HibernateUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

class HibernateRunnerTest {

    @Test
    public void checkOneToOne() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var user = User.builder()
                .username("kamenskiy400@gmail.com")
                .build();
        var profile = Profile.builder()
                .id(10L)
                .street("Gorbatogo 10")
                .language("RU")
                .build();
        session.save(user);
        profile.setUser(user);
        session.save(profile);
        session.getTransaction().commit();
    }

    @Test
    public void checkOrchanRemoval() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var company = session.get(Company.class, 1);
        company.getUsers().removeIf(user -> user.getId().equals(9L));

        session.getTransaction().commit();
    }

    @Test
    public void addNewUserAndCompany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var company = Company.builder()
                .name("Apple")
                .build();

        //TRANSIENT
        var user = User.builder()
                .username("kamenskiy100@gmail.com")
                .build();

        company.addUser(user);
        session.save(company);

        session.getTransaction().commit();
    }

    @Test
    public void checkOneToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();

        var company = session.get(Company.class, 1);
        System.out.println(company.getUsers());

        session.getTransaction().commit();
    }

    @Test
    public void testHibernateApi() {
      /*  var user = User.builder()
                .username("kamenskiy1@gmail.com")
                .firstname("Andrey")
                .lastname("Kamenskiy")
                .birthDate(LocalDate.of(1990, 1, 23))
                .age(34)
                .build();
        var sql = """
                INSERT INTO 
                %s 
                (%s)
                VALUES 
                (%s)
                """;
        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());

        Field[] fields = user.getClass().getDeclaredFields();

        var columnNames = Arrays.stream(fields).map(field -> Optional.ofNullable(field
                                .getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName()))
                .collect(Collectors.joining(", "));

        var columnValues = Arrays.stream(fields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));

        try (Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/my_db", "my_user", "123");
             var statement = connection
                     .prepareStatement(sql.formatted(tableName, columnNames, columnValues));
        ) {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                statement.setObject(i + 1, fields[i].get(user)); //получаем значения каждого поля объекта user
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }*/
    }
}