package com.example.demo.controllers;

import com.example.demo.dto.BookDTO;
import com.example.demo.services.BookService;
import com.example.demo.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/student/{studentId}")
    public List<BookDTO> getStudentBooks(@PathVariable Long studentId) {
        return bookService.getStudentBooks(studentId);
    }

    @PostMapping()
    public void addNewBook(@RequestBody Book book) {
        bookService.addNewBook(book);
    }
}
