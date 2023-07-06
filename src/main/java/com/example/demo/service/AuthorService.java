package com.example.demo.service;

import com.tej.JooQDemo.jooq.sample.model.Tables;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Authors;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    DSLContext context;
    public List<Authors> getAuthors(){
        return context
                .selectFrom(Tables.AUTHORS)
                .fetchInto(Authors.class);
    }
    public void insertAuthors(Authors author){
        context
                .insertInto(Tables.AUTHORS, Tables.AUTHORS.NAME)
                .values(author.getName())
                .execute();
    }
}
