package com.example.jooqdemo.controller;

import com.example.jooqdemo.service.TestService;
import com.generator.tables.pojos.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(method = RequestMethod.GET,value = "/delete/{id}")
    public void delete(@PathVariable("id")int id){
        testService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/insert")
    public void insert(@RequestBody Users user){
        testService.insert(user);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public void update(@RequestBody Users user){
        testService.update(user);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{id}/select")
    public Users select(@PathVariable("id")int id){
        return testService.selectById(id);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/selectAll/{pageNum}/{pageSize}")
    public List<Users> selectAll(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        return testService.selectAll(pageNum, pageSize);

    }
}
