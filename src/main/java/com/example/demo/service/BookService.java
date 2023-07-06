package com.example.demo.service;

import com.example.demo.BookResponse;
import com.tej.JooQDemo.jooq.sample.model.Tables;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Authors;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Books;
import org.jooq.DSLContext;
import org.jooq.Record4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {
    @Autowired
    DSLContext context;
    AuthorService authorService;

    public BookService(AuthorService authorS){
        this.authorService = authorS;
    }


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

    public List<BookResponse> getBooksWithAuthors() {
        return context.select(
                        Tables.BOOKS.ID,
                        Tables.BOOKS.TITLE,
                        Tables.AUTHORS.ID,
                        Tables.AUTHORS.NAME
                )
                .from(Tables.BOOKS)
                .join(Tables.AUTHORS)
                .on(Tables.BOOKS.AUTHOR_ID.eq(Tables.AUTHORS.ID))
                .fetch()
                .map(this::generateBookResponse);
    }

    private BookResponse generateBookResponse(Record4<Integer, String, Integer, String> record) {
        Books book = record.into(Tables.BOOKS).into(Books.class);
        Integer authorId = record.get(Tables.AUTHORS.ID);
        Authors author = authorService.getAuthorById(authorId);
        BookResponse bookDetails = new BookResponse();
        bookDetails.setBook(book);
        bookDetails.setAuthor(author);
        return bookDetails;
    }

        public Books getBookById(Integer bookId) {
            return context.selectFrom(Tables.BOOKS)
                    .where(Tables.BOOKS.ID.eq(bookId))
                    .fetchOneInto(Books.class);
        }
}