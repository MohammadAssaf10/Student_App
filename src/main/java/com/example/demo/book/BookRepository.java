package com.example.demo.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @NonNull
    @Override
    @Query(value = "SELECT * FROM book ORDER BY id", nativeQuery = true)
    List<Book> findAll();

    @Query(value = "SELECT * FROM book WHERE name = :bookName and student_id = :studentId", nativeQuery = true)
    Optional<Book> findByNameAndStudentId(String bookName, Long studentId);

}