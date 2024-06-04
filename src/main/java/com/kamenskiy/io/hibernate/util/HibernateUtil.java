package com.kamenskiy.io.hibernate.util;

import com.kamenskiy.io.hibernate.converter.BirthdayConverter;
import com.kamenskiy.io.hibernate.entity.Birthday;
import com.kamenskiy.io.hibernate.entity.Company;
import com.kamenskiy.io.hibernate.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAttributeConverter(BirthdayConverter.class, true);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        //        configuration.addAnnotatedClass(User.class);
        //        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        return configuration.buildSessionFactory();
    }
}
