package com.example.demo.services;

import com.example.demo.dto.StudentIdCardDTO;
import com.example.demo.entities.Student;
import com.example.demo.entities.StudentIdCard;
import com.example.demo.repositories.StudentIdCardRepository;
import com.example.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentIdCardService {
    @Autowired
    private StudentIdCardRepository studentIdCardRepository;
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentIdCard> getStudentCardId() {
        return studentIdCardRepository.findAll();
    }

    public void addNewStudentIdCard(StudentIdCard studentIdCard) {
        Optional<Student> studentOptional = studentRepository.findByEmail(studentIdCard.getStudent().getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Student email already exist");
        }
        studentIdCardRepository.save(studentIdCard);
    }

    public void addNewByStudentId(StudentIdCardDTO studentIdCardDTO) {
        Student optionalStudent = studentRepository.findById(studentIdCardDTO.getStudentId())
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + studentIdCardDTO.getStudentId() + " doesn't exist"));
        Optional<StudentIdCard> optionalStudentIdCard = studentIdCardRepository.findByStudentId(studentIdCardDTO.getStudentId());
        if (optionalStudentIdCard.isPresent()) {
            throw new IllegalStateException("Student with id " + studentIdCardDTO.getStudentId() + " have card number");
        }
        StudentIdCard studentIdCard = new StudentIdCard(studentIdCardDTO.getCardNumber(), optionalStudent);
        studentIdCardRepository.save(studentIdCard);
    }

    public void deleteStudentIdCard(Long studentIdCardId) {
        boolean isStudentIdCardExist = studentIdCardRepository.existsById(studentIdCardId);
        if (!isStudentIdCardExist) {
            throw new IllegalStateException("Student card with id " + studentIdCardId + " doesn't exist");
        }
        studentIdCardRepository.deleteCardById(studentIdCardId);
    }
}
