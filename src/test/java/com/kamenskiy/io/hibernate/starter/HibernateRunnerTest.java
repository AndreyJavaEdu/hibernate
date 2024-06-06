package com.kamenskiy.io.hibernate.starter;

import com.kamenskiy.io.hibernate.entity.Chat;
import com.kamenskiy.io.hibernate.entity.Company;
import com.kamenskiy.io.hibernate.entity.Profile;
import com.kamenskiy.io.hibernate.entity.User;
import com.kamenskiy.io.hibernate.entity.UserChat;
import com.kamenskiy.io.hibernate.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;


class HibernateRunnerTest {
    @Test
    public void checkHQL() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        String name = "Pavel";

        var users = session.createNamedQuery("findUserByNameAndCompany", User.class)
                .setParameter("firstname", name)
                .setParameter("companyName", "Yandex")
                .list();

        int i = session.createMutationQuery("update User u set u.role = 'ADMIN'").executeUpdate();
        System.out.println(users);

        session.getTransaction().commit();
    }

    @Test
    public void checkInheritance() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var company = Company.builder()
                .name("Yandex")
                .build();
        /*
        session.save(company);
        var programmer = Programmer.builder()
                .username("ivan123@yandex.ru")
                .language(Language.JAVA)
                .build();
        session.save(programmer);

        var manager = Manager.builder()
                .username("alex@yandex.ru")
                .project("Java Enterprise")
                .build();

        session.save(manager);

        session.flush();
        session.clear();
        var programmer1 = session.get(Programmer.class, 1L);
        var manager1 = session.get(User.class, 2L);
         */
        session.getTransaction().commit();
    }

    @Test
    public void checkH2() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var company = Company.builder()
                .name("Yandex")
                .build();
        session.save(company);
        session.getTransaction().commit();
    }

    @Test
    public void checkManyToMany3() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Chat chat = session.get(Chat.class, 1L);
        var userChat = session.get(UserChat.class, 1L);
        /*
        User user = User.builder()
                .username("kjhjkhkhah")
                .build();
        session.save(user);
        userChat.setUser(user);
        session.save(userChat);

         */
        session.getTransaction().commit();
    }


    @Test
    public void checkManyToMany2() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Chat chat = session.get(Chat.class, 1L);
        User user = session.get(User.class, 12L);

/*        var userChat = UserChat.builder()
                .createdBy("Andrey")
                .createdAt(Instant.now())
                .build();
        userChat.setChat(chat);
        userChat.setUser(user);
        session.save(userChat);
        session.getTransaction().commit();

 */
    }

    @Test
    public void checkManyToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Chat chat = Chat.builder()
                .name("javaGuru")
                .build();
        User user = session.get(User.class, 12L);
//        user.addChat(chat);
        session.save(chat);
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    @Test
    public void checkOneToOne() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var user = User.builder()
                .username("ivanov1@gmail.com")
                .build();
        var profile = Profile.builder()
//                .id(10L)
                .street("Gorbatogo 12")
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
//        var user = User.builder()
//                .username("kamenskiy100@gmail.com")
//                .build();

//        company.addUser(user);
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