package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Books;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Users;
import org.jooq.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public List<Users> getUsers(){
        return this.userService.getUsers();
    }
    @PostMapping
    public void postUser(@RequestBody Users user){
        this.userService.insertUsers(user);
    }
}
