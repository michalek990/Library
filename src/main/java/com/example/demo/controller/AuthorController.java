package com.example.demo.controller;

import com.example.demo.service.AuthorService;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Authors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @GetMapping
    public List<Authors> getAuthor(){
        return this.authorService.getAuthors();
    }

    @GetMapping("/aa")
    public List<Authors> getAuthorWithBooks(){
        return this.authorService.getAuthorsWithBooks();
    }

    @PostMapping
    public void postAuthor(@RequestBody Authors author){
        this.authorService.insertAuthors(author);
    }

    @DeleteMapping("/{Id}")
    public void deleteAuthor(@PathVariable Long Id){
        this.authorService.deleteAuthor(Id);
    }
}
