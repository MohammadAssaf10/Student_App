package com.example.demo.services;

import com.example.demo.entities.Enrolment;
import com.example.demo.repositories.EnrolmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolmentService {
    @Autowired
    private EnrolmentRepository enrolmentRepository;

    public List<Enrolment> getEnrolments(){
        return enrolmentRepository.findAll();
    }
}
