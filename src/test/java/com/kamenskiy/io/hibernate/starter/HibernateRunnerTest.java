package com.kamenskiy.io.hibernate.starter;

import com.kamenskiy.io.hibernate.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
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