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
    private BookRepository bookRepository;
    @Autowired
    private StudentRepository studentRepository;


    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void addNewBook(BookDTO bookDTO) {
        Student optionalStudent = studentRepository.findById(bookDTO.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + bookDTO.getId() + " doesn't exist"));
        Optional<Book> optionalBook = bookRepository.findByNameAndStudentId(bookDTO.getName(), bookDTO.getId());
        if (optionalBook.isPresent()) {
            throw new IllegalStateException(optionalStudent.getName() + " student have " + bookDTO.getName() + " book");
        }
        Book book = new Book(bookDTO.getName(), optionalStudent);
        bookRepository.save(book);
    }

    public List<BookDTO> getStudentBooks(Long studentId) {
        studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + studentId + " doesn't exist"));
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

    public void deleteBook(Long bookId) {
        boolean isBookExist = bookRepository.existsById(bookId);
        if (!isBookExist) {
            throw new IllegalStateException("Book with id " + bookId + " doesn't exist");
        }
        bookRepository.deleteById(bookId);
    }
}
