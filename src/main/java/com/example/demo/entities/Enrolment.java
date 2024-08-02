package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity(name = "Enrolment")
@Table(name = "enrolment")
public class Enrolment {

    @EmbeddedId
    private EnrolmentId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(
            name = "student_id",
            foreignKey = @ForeignKey(
                    name = "enrolment_student_id_fk"
            )
    )
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(
            name = "course_id",
            foreignKey = @ForeignKey(
                    name = "enrolment_course_id_fk"
            )
    )
    private Course course;


    @Column(
            name = "created_at",
            nullable = false
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Damascus")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;


    public Enrolment(EnrolmentId id,
                     Student student,
                     Course course,
                     Date createdAt) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.createdAt = createdAt;
    }

    public Enrolment(Student student,
                     Course course,
                     Date createdAt) {
        this.student = student;
        this.course = course;
        this.createdAt = createdAt;
    }

    public Enrolment() {
    }

    public EnrolmentId getId() {
        return id;
    }

    public void setId(EnrolmentId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}