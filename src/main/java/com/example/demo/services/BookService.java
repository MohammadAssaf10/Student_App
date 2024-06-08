package com.example.demo.services;

import com.example.demo.dto.BookDTO;
import com.example.demo.entities.Book;
import com.example.demo.entities.Student;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    @Autowired
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    public BookService(BookRepository bookRepository, StudentRepository studentRepository) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }


    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void addNewBook(Book book) {
        Student optionalStudent = studentRepository.findById(book.getStudent().getId())
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + book.getStudent().getId() + " doesn't exist"));
        Optional<Book> optionalBook = bookRepository.findByNameAndStudentId(book.getName(), book.getStudent().getId());
        if (optionalBook.isPresent()) {
            throw new IllegalStateException(optionalStudent.getName() + " student have " + book.getName() + " book");
        }
        bookRepository.save(book);
    }

    public List<BookDTO> getStudentBooks(Long studentId) {
        List<BookDTO> studentBooksList = new ArrayList<>();
        List<Book> booksList = bookRepository.findByStudentId(studentId);
        for (Book book : booksList) {
            studentBooksList.add(convertToDTO(book));
        }
        return studentBooksList;
    }

    private BookDTO convertToDTO(Book book) {
        return new BookDTO(book.getId(), book.getName());
    }
}
