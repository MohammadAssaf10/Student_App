package com.example.demo.controllers;

import com.example.demo.entities.Enrolment;
import com.example.demo.services.EnrolmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/enrolments")
public class EnrolmentController {

    @Autowired
    private EnrolmentService enrolmentService;

    @GetMapping
    public List<Enrolment> getEnrolments(){
        return  enrolmentService.getEnrolments();
    }
}
