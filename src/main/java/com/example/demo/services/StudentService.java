package com.example.demo.services;

import com.example.demo.dto.BaseResponse;
import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public ResponseEntity<BaseResponse<List<Student>>> getStudents() {
        final List<Student> students = studentRepository.findAll();
        return new ResponseEntity<>(new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), students), HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse<Void>> addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            return new ResponseEntity<>(new BaseResponse<>(HttpStatus.CONFLICT.value(), "Email already exist", null), HttpStatus.CONFLICT);
        }
        studentRepository.save(student);
        return new ResponseEntity<>(new BaseResponse<>(HttpStatus.OK.value(), "Added Successfully", null), HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse<Void>> deleteStudent(Long studentId) {
        boolean isStudentExist = studentRepository.existsById(studentId);
        if (!isStudentExist) {
            return new ResponseEntity<>(new BaseResponse<>(HttpStatus.NOT_FOUND.value(), "Student with id " + studentId + " doesn't exist", null), HttpStatus.NOT_FOUND);
        }
        studentRepository.deleteById(studentId);
        return new ResponseEntity<>(new BaseResponse<>(HttpStatus.OK.value(), "Deleted Successfully", null), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<BaseResponse<Void>> updateStudent(Long studentId, String name, String email) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            return new ResponseEntity<>(new BaseResponse<>(HttpStatus.CONFLICT.value(), "Student with id " + studentId + " doesn't exist", null), HttpStatus.CONFLICT);
        }
        if (name != null && !name.isEmpty() && !Objects.equals(student.get().getName(), name)) {
            student.get().setName(name);
        }
        if (email != null && !email.isEmpty() && !Objects.equals(student.get().getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findByEmail(email);
            if (studentOptional.isPresent()) {
                return new ResponseEntity<>(new BaseResponse<>(HttpStatus.CONFLICT.value(), "Email already exist", null), HttpStatus.CONFLICT);
            }
            student.get().setEmail(email);
        }
        return new ResponseEntity<>(new BaseResponse<>(HttpStatus.OK.value(), "Updated Successfully", null), HttpStatus.OK);
    }
}
