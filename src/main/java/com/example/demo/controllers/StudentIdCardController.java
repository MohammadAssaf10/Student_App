package com.example.demo.controllers;


import com.example.demo.dto.StudentIdCardDTO;
import com.example.demo.entities.StudentIdCard;
import com.example.demo.services.StudentIdCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/studentCardId")
public class StudentIdCardController {
    @Autowired
    private StudentIdCardService studentIdCardService;

    @GetMapping
    public List<StudentIdCard> getStudentIdCard() {
        return studentIdCardService.getStudentCardId();
    }

    @PostMapping()
    public void addNewStudentIdCard(@RequestBody StudentIdCard studentIdCard){
        studentIdCardService.addNewStudentIdCard(studentIdCard);
    }

    @PostMapping(value = "/byStudentId")
    public void addNewByStudentId(@RequestBody StudentIdCardDTO studentIdCardDTO){
        studentIdCardService.addNewByStudentId(studentIdCardDTO);
    }

    @DeleteMapping(path = "{studentIdCardId}")
    public void deleteStudentIdCard(@PathVariable("studentIdCardId") Long studentIdCardId){
        studentIdCardService.deleteStudentIdCard(studentIdCardId);
    }
}
