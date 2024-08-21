package com.example.demo.repositories;

import com.example.demo.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "SELECT * FROM course WHERE name = :name and department = :department", nativeQuery = true)
    Optional<Course> findByNameAndDepartment(String name, String department);
}