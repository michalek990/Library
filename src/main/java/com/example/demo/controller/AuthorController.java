package com.example.demo.controller;

import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Authors;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @GetMapping
    public List<com.tej.JooQDemo.jooq.sample.model.tables.pojos.Authors> getAuthor(){
        return this.authorService.getAuthors();
    }
    @PostMapping
    public void postAuthor(@RequestBody Authors author){
        this.authorService.insertAuthors(author);
    }
}
