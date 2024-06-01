package com.kamenskiy.io.hibernate.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Birthday(LocalDate birthDate) {
    public Long getAge(){
       return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }
}
