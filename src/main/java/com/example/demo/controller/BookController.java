package com.example.demo.controller;

import com.example.demo.BookResponse;
import com.example.demo.service.BookService;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/aa")
    public List<BookResponse> getBookss(){
        return this.bookService.getBooksss();
    }

    @PostMapping
    public void postBook(@RequestBody Books book){
        this.bookService.insertBook(book);
    }

    @DeleteMapping("/{Id}")
    public void deleteBook(@PathVariable Long Id){
        this.bookService.deleteBook(Id);
    }

    @PutMapping("/{id}")
    public void updateBookTitleById(@PathVariable int id, @RequestBody String newTitle) {
       this.bookService.updateBookTitleById(id, newTitle);
    }
}