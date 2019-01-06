package com.example.jooqdemo.service.impl;

import com.example.jooqdemo.service.TestService;
import com.generator.tables.daos.UsersDao;
import com.generator.tables.pojos.Users;
import com.generator.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    DSLContext dslContext;

    com.generator.tables.Users u = com.generator.tables.Users.USERS.as("u");

    @Override
    public void insert(Users users) {
        dslContext.insertInto(u).columns(u.FIRSTNAME,u.GENDER,u.AGE,u.CITY,u.PHONENUMBER,u.PROVINCE,u.SECONDNAME)
                .values(users.getFirstname(),users.getGender(),users.getAge(),users.getCity(),users.getPhonenumber(),users.getProvince(),users.getSecondname())
                .execute();
    }

    @Override
    public void delete(Integer id) {
        dslContext.delete(u).where(u.ID.eq(id)).execute();
    }

    @Override
    public int update(Users users) {
        dslContext.update(u).set(u.AGE,users.getAge())
                .set(u.CITY,users.getCity())
                .set(u.FIRSTNAME,users.getFirstname())
                .set(u.GENDER, users.getGender())
                .set(u.PHONENUMBER,users.getPhonenumber())
                .set(u.PROVINCE,users.getProvince())
                .set(u.SECONDNAME,users.getSecondname())
                .where(u.ID.eq(users.getId()))
                .execute();
        return 0;
    }

    @Override
    public Users selectById(Integer id) {
        return new UsersDao(dslContext.configuration()).fetchOneById(id);
    }

    @Override
    public List<Users> selectAll(Integer pageNum, Integer pageSize) {
        return dslContext.select().from(u).orderBy(u.ID).limit(pageSize).offset(pageNum).fetch()
                .map(record -> {
                    Users users = record.into(Users.class);
                    return users;
                });
    }
}
