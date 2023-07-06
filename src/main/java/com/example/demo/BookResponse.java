package com.example.demo;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Authors;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Books;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {
    private Books book;
    private Authors author;
}