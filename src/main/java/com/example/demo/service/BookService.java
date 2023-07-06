package com.example.demo.service;

import com.tej.JooQDemo.jooq.sample.model.Tables;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Books;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {
    @Autowired
    DSLContext context;
    public List<Books> getBooks(){
        return context
                .selectFrom(Tables.BOOKS)
                .fetchInto(Books.class);
    }
    public void insertBook(Books book){
        context
                .insertInto(Tables.BOOKS, Tables.BOOKS.TITLE,
                        Tables.BOOKS.AUTHOR_ID)
                .values(book.getTitle(), book.getAuthorId())
                .execute();
    }

    public void deleteBook(Long Id){
        context
                .deleteFrom(Tables.BOOKS)
                .where(Tables.BOOKS.ID.eq(Math.toIntExact(Id)))
                .execute();
    }

    public void updateBookTitleById(int id, String newTitle) {

        context.update(Tables.BOOKS)
                .set(Tables.BOOKS.TITLE, newTitle)
                .where(Tables.BOOKS.ID.eq(id))
                .execute();
    }

    public List<Books> getBooksAndAuthors() {

        List<Books> books  = context.select(Tables.BOOKS.TITLE, Tables.AUTHORS.NAME)
                .from(Tables.BOOKS)
                .leftJoin(Tables.AUTHORS).on(Tables.BOOKS.AUTHOR_ID.eq(Tables.AUTHORS.ID))
                        .fetch()
                        .into(Books.class);
        return books;
    }
}