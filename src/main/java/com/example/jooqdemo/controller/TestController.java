package com.example.jooqdemo.controller;

import com.example.jooqdemo.model.ResponseData;
import com.generator.tables.daos.UsersDao;
import com.generator.tables.pojos.Users;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    DSLContext dslContext;

    com.generator.tables.Users u = com.generator.tables.Users.USERS.as("u");
    @RequestMapping(method = RequestMethod.GET,value = "/delete/{id}")
    public ResponseData delete(@PathVariable("id")int id){
        dslContext.delete(u).where(u.ID.eq(id)).execute();
        return new ResponseData<>("Successfully Delete");
    }

    @RequestMapping(method = RequestMethod.POST,value = "/insert")
    public ResponseData insert(@RequestBody Users users){
        dslContext.insertInto(u).columns(u.FIRSTNAME,u.GENDER,u.AGE,u.CITY,u.PHONENUMBER,u.PROVINCE,u.SECONDNAME)
                .values(users.getFirstname(),users.getGender(),users.getAge(),users.getCity(),users.getPhonenumber(),users.getProvince(),users.getSecondname())
                .execute();
        return new ResponseData<>("Successfully Insert");
    }

    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public ResponseData update(@RequestBody Users users){
        dslContext.update(u).set(u.AGE,users.getAge())
                .set(u.CITY,users.getCity())
                .set(u.FIRSTNAME,users.getFirstname())
                .set(u.GENDER, users.getGender())
                .set(u.PHONENUMBER,users.getPhonenumber())
                .set(u.PROVINCE,users.getProvince())
                .set(u.SECONDNAME,users.getSecondname())
                .where(u.ID.eq(users.getId()))
                .execute();
        return new ResponseData<>("Successfully Update");
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{id}/select")
    public ResponseData select(@PathVariable("id")int id){
        return new ResponseData<>(new UsersDao(dslContext.configuration()).fetchOneById(id));
    }

    @RequestMapping(method = RequestMethod.GET,value = "/selectAll/{pageNum}/{pageSize}")
    public ResponseData selectAll(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        return new ResponseData<>(dslContext.select().from(u).orderBy(u.ID).limit(pageSize).offset(pageNum).fetch().map(record -> {
            Users users = record.into(Users.class);
            return users;
        }));

    }
}
