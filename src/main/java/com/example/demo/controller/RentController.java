package com.example.demo.controller;

import com.example.demo.RentResponse;
import com.example.demo.service.BookService;
import com.example.demo.service.RentService;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Books;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Rents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rents")
public class RentController {
    @Autowired
    RentService rentService;
    @GetMapping
    public List<Rents> getRents(){
        return this.rentService.getRents();
    }

    @GetMapping("/withUsers")
    public List<RentResponse> getRentsWithAllInfo(){
        return this.rentService.getRentsWithUsersAndBooks();
    }

    @PostMapping
    public void postRent(@RequestBody Rents rent){
        this.rentService.insertRent(rent);
    }

    @DeleteMapping("/{Id}")
    public void deleteRent(@PathVariable Long Id){
        this.rentService.deleteRent(Id);
    }
}
