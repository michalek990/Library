package com.example.demo.service;

import com.example.demo.RentResponse;
import com.tej.JooQDemo.jooq.sample.model.Tables;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Books;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Rents;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Users;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentService {
    @Autowired
    DSLContext context;
    UserService userService;
    BookService bookService;

    public RentService(UserService userS, BookService bookS){
        this.bookService = bookS;
        this.userService = userS;
    }

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
    public void deleteRent(Long Id){
        context
                .deleteFrom(Tables.RENTS)
                .where(Tables.RENTS.ID.eq(Math.toIntExact(Id)))
                .execute();
    }

    public List<RentResponse> getRentsWithUsersAndBooks(){
        return context.select(
                        Tables.RENTS.ID,
                        Tables.RENTS.BOOK_ID,
                        Tables.RENTS.USER_ID,
                        Tables.RENTS.RENT_DATE,
                        Tables.RENTS.RETURN_DATE,
                        Tables.USERS.ID,
                        Tables.USERS.USERNAME,
                        Tables.BOOKS.ID,
                        Tables.BOOKS.TITLE
                )
                .from(Tables.RENTS)
                .join(Tables.USERS)
                .on(Tables.USERS.ID.eq(Tables.RENTS.USER_ID))
                .join(Tables.BOOKS)
                .on(Tables.BOOKS.ID.eq(Tables.RENTS.BOOK_ID))
                .fetch()
                .map(this::generateRentResponse);
    }

    private RentResponse generateRentResponse(Record9<Integer, Integer, Integer, LocalDate, LocalDate, Integer, String, Integer, String> record){
        Rents rent = record.into(Tables.RENTS).into(Rents.class);
        Integer userId = record.get(Tables.USERS.ID);
        Integer bookId = record.get(Tables.BOOKS.ID);
        Users user = userService.getUserById(userId);
        Books book = bookService.getBookById(bookId);
        RentResponse rentDetails = new RentResponse();
        rentDetails.setRent(rent);
        rentDetails.setBook(book);
        rentDetails.setUser(user);
        return rentDetails;
    }



}
