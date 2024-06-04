package com.kamenskiy.io.hibernate.starter;

import com.kamenskiy.io.hibernate.entity.*;
import com.kamenskiy.io.hibernate.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StudentsTest {

    @Test
    public void testManyToManySaveTrainerAndCourses() throws Exception {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var trainer = Trainer.builder()
                .firstName("Aleks")
                .lastName("Gofman")
                .build();
//
        var course1 = Course.builder()
                .name("SQL")
                .build();
        var course2 = session.get(Course.class, 2);
        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        TrainerCourse trainerCourse = TrainerCourse.builder()
                .build();
        session.save(trainer);
        for (Course course : courses) {
            session.persist(course);
            trainerCourse.setCourse(course);
            trainerCourse.setTrainer(trainer);
            session.merge(trainerCourse);
        }
        session.getTransaction().commit();
    }

    @Test
    public void testOneToOneRemoveCourseJavaEnterprise() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var course = session.get(Course.class, 1);
        session.remove(course);
        session.getTransaction().commit();
    }

    @Test
    public void testOneToOneAddStudentOnJavaEnterpriseCourse() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var course = session.get(Course.class, 1);
        var student = Student.builder()
                .firstName("Gregor")
                .lastName("Volodin")
                .build();
        course.addStudent(student);
        session.getTransaction().commit();
    }

    @Test
    public void testOneToOneDeleteStudentsOnJavaEnterpriseProfile() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        Course course = session.get(Course.class, 1);
        course.getStudents().removeIf(student -> student.getProfile().getAverageScore() < 6);
        session.getTransaction().commit();
    }

    @Test
    public void testOneToOneAddStudentsOnJavaEnterpriseProfile() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var student1 = session.get(Student.class, 1L);
        var student5 = session.get(Student.class, 5L);
        var student6 = session.get(Student.class, 6L);

        var profile1 = StudentProfile.builder()
                .averageScore((short) 4)
                .build();
        profile1.setStudent(student1);
        var profile5 = StudentProfile.builder()
                .averageScore((short) 8)
                .build();
        profile5.setStudent(student5);
        var profile6 = StudentProfile.builder()
                .averageScore((short) 7)
                .build();
        profile6.setStudent(student6);
        session.persist(profile1);
        session.persist(profile5);
        session.persist(profile6);
        session.getTransaction().commit();
    }

    @Test
    public void testOneToManyAllStudentsOnJavaEnterprise() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Course course = session.get(Course.class, 1);
        List<Student> students = course.getStudents();
        System.out.println(students);
        session.getTransaction().commit();
    }

    @Test
    public void testOneToMany2() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Course course = Course.builder()
                .name("Java")
                .build();

        var student1 = Student.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .build();
        var student2 = Student.builder()
                .firstName("Olga")
                .lastName("Vladimirova")
                .build();
        var student3 = Student.builder()
                .firstName("Oksana")
                .lastName("Igorevna")
                .build();
        course.addStudent(student1);
        course.addStudent(student2);
        course.addStudent(student3);
        session.save(course);

        session.getTransaction().commit();
    }

    @Test
    public void testOneToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Course course = session.get(Course.class, 1);
        var student1 = Student.builder()
                .firstName("Vova")
                .lastName("Vavin")
                .build();
        var student2 = Student.builder()
                .firstName("Gena")
                .lastName("Genin")
                .build();
        student1.addCourse(course);
        student2.addCourse(course);
        session.persist(student1);
        session.persist(student2);

        session.getTransaction().commit();
    }
}
