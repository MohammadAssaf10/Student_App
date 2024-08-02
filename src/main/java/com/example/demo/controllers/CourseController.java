package com.example.demo.controllers;


import com.example.demo.entities.Course;
import com.example.demo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    List<Course> getCourses() {
        return courseService.getCourses();
    }
}
