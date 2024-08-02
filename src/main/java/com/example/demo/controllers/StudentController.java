package com.example.demo.controllers;


import com.example.demo.dto.BaseResponse;
import com.example.demo.entities.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentServices;

    @GetMapping
    public ResponseEntity<BaseResponse<List<Student>>> getStudents() {
        return studentServices.getStudents();
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> addNewStudent(@RequestBody Student student) {
        return studentServices.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<BaseResponse<Void>> deleteStudent(@PathVariable("studentId") Long studentId) {
        return  studentServices.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<BaseResponse<Void>> updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        return  studentServices.updateStudent(studentId, name, email);
    }

}
