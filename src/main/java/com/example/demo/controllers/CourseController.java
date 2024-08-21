package com.example.demo.controllers;


import com.example.demo.dto.BaseResponse;
import com.example.demo.entities.Course;
import com.example.demo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    ResponseEntity<BaseResponse<List<Course>>> getCourses() {
        return courseService.getCourses();
    }

    @PostMapping
    ResponseEntity<BaseResponse<Void>> addNewCourse(@RequestBody Course course){
        return  courseService.addNewCourse(course);
    }
}
