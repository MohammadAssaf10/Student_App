package com.example.demo.dto;

public class StudentIdCardDTO {
    private  Long studentId;
    private  String cardNumber;

    public StudentIdCardDTO(Long studentId, String cardNumber) {
        this.studentId = studentId;
        this.cardNumber = cardNumber;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "StudentIdCardDTO{" +
                "studentId=" + studentId +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
