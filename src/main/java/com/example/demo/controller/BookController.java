package com.example.demo.controller;

import com.example.demo.service.BookService;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookService bookService;
    @GetMapping
    public List<Books> getBooks(){
        return this.bookService.getBooks();
    }
    @PostMapping
    public void postBook(@RequestBody Books book){
        this.bookService.insertBook(book);
    }
}