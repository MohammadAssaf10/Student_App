package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @NonNull
    @Override
    @Query(value = "SELECT * FROM student ORDER BY id", nativeQuery = true)
    List<Student> findAll();

    @Query(value = "SELECT * FROM student WHERE email = :email", nativeQuery = true)
    Optional<Student> findByEmail(String email);

}