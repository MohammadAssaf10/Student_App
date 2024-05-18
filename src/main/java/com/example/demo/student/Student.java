package com.example.demo.student;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirthday;
    @Transient
    private Integer age;

    public Student() {
    }

    public Student(Long id,
                   String name,
                   String email,
                   LocalDate dateOfBirthday) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirthday = dateOfBirthday;
    }

    public Student(String name, String email, LocalDate dateOfBirthday) {
        this.name = name;
        this.email = email;
        this.dateOfBirthday = dateOfBirthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirthday,LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirthday=" + dateOfBirthday +
                ", age=" + age +
                '}';
    }
}
