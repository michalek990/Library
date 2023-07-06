package com.example.demo;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Books;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Rents;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Users;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentResponse {
    private Rents rent;
    private Users user;
    private Books book;
}