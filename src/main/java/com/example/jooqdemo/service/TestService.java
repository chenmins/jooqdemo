package com.example.jooqdemo.service;

import com.generator.tables.pojos.Users;

import java.util.List;

public interface TestService {
    void insert(Users users);
    void delete(Integer id);
    int update(Users users);
    Users selectById(Integer id);
    List<Users> selectAll(Integer pageNum, Integer pageSize);
}
