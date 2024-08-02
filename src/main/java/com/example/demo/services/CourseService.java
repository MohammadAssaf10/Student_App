package com.example.demo.services;

import com.example.demo.entities.Course;
import com.example.demo.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }
}
