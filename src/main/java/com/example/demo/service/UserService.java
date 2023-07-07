package com.example.demo.service;

import com.tej.JooQDemo.jooq.sample.model.Tables;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Users;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    DSLContext context;
    public List<Users> getUsers(){
        return context
                .selectFrom(Tables.USERS)
                .fetchInto(Users.class);
    }
    public void insertUsers(Users user){
        context
                .insertInto(Tables.USERS, Tables.USERS.FIRSTNAME,
                        Tables.USERS.LASTNAME, Tables.USERS.USERNAME)
                .values(user.getFirstname(), user.getLastname(), user.getUsername())
                .execute();
    }

    public void deleteUser(Long Id){
        context
                .deleteFrom(Tables.USERS)
                .where(Tables.USERS.ID.eq(Math.toIntExact(Id)))
                .execute();
    }

    public Users getUserById(Integer userId) {
        return context.selectFrom(Tables.USERS)
                .where(Tables.USERS.ID.eq(userId))
                .fetchOneInto(Users.class);
    }
}
