package com.example.demo.services;

import com.example.demo.dto.BaseResponse;
import com.example.demo.entities.Course;
import com.example.demo.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public ResponseEntity<BaseResponse<List<Course>>> getCourses() {
        try {
            List<Course> courses = courseRepository.findAll();
            return new ResponseEntity<>(new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), courses), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error from getCourses function" + e);
            return new ResponseEntity<>(new BaseResponse<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), null), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BaseResponse<Void>> addNewCourse(Course course) {
        try {
            Optional<Course> optionalCourse = courseRepository.findByNameAndDepartment(course.getName(), course.getDepartment());
            if (optionalCourse.isPresent()) {
                return new ResponseEntity<>(
                        new BaseResponse<>(
                                HttpStatus.CONFLICT.value(),
                                course.getName() + " course is find in the " + course.getDepartment() + " department",
                                null
                        ),
                        HttpStatus.CONFLICT);
            }
            courseRepository.save(course);
            return new ResponseEntity<>(new BaseResponse<>(HttpStatus.OK.value(), "Added Successfully", null), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error from addNewCourse function" + e);
            return new ResponseEntity<>(new BaseResponse<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), null), HttpStatus.NOT_FOUND);
        }
    }
}
