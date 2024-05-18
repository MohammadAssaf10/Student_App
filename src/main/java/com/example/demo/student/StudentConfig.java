//package com.example.demo.student;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//
//@Configuration
//public class StudentConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
//        return args -> {
//            Student mohamad = new Student(
//                    "Mohamad",
//                    "Mohamad@gmail.com",
//                    LocalDate.of(2001, Month.OCTOBER, 12)
//            );
//            Student alex = new Student(
//                    "Alex",
//                    "Alex@gmail.com",
//                    LocalDate.of(2004, Month.OCTOBER, 12)
//            );
//            studentRepository.saveAll(List.of(mohamad, alex));
//        };
//    }
//}
