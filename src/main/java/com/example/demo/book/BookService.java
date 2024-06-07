package com.example.demo.book;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
