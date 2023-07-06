package com.example.demo.service;

import com.tej.JooQDemo.jooq.sample.model.Tables;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Books;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Rents;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {
    @Autowired
    DSLContext context;
    public List<Rents> getRents(){
        return context
                .selectFrom(Tables.RENTS)
                .fetchInto(Rents.class);
    }
    public void insertRent(Rents rent){
        context
                .insertInto(Tables.RENTS, Tables.RENTS.USER_ID,
                        Tables.RENTS.BOOK_ID, Tables.RENTS.RENT_DATE, Tables.RENTS.RETURN_DATE)
                .values(rent.getUserId(), rent.getBookId(), rent.getRentDate(), rent.getReturnDate())
                .execute();
    }
}
