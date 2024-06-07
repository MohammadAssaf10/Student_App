package com.example.demo.book;

import com.fasterxml.jackson.annotation.JsonView;
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

    @JsonView(Book.class)
    @PostMapping()
    public void addNewBook(@RequestBody Book book) {
        bookService.addNewBook(book);
    }
}
