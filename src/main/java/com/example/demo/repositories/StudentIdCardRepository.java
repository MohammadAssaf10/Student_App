package com.example.demo.repositories;

import com.example.demo.entities.StudentIdCard;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentIdCardRepository extends JpaRepository<StudentIdCard, Long> {

    @Query(value = "SELECT * FROM student_id_card WHERE student_id = :studentId", nativeQuery = true)
    Optional<StudentIdCard> findByStudentId(Long studentId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM student_id_card WHERE id = :id",nativeQuery = true)
    void deleteCardById(Long id);
}