package com.example.demo.controllers;


import com.example.demo.entities.Student;
import com.example.demo.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @Autowired
    private StudentServices studentServices;

    @GetMapping
    public List<Student> getStudents() {
        return studentServices.getStudents();
    }

    @PostMapping
    public void registerStudent(@RequestBody Student student) {
        studentServices.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentServices.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentServices.updateStudent(studentId, name, email);
    }

}
