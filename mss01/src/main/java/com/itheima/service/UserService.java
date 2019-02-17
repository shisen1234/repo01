package com.itheima.service;

import com.itheima.domain.PageBean;
import com.itheima.domain.User;

import java.util.List;

public interface UserService {
    public User login(User user);

    List<User> findAll();

    void add(User user);

    void delete(int id);

    User findUser(int id);

    Integer findTotalCount();

    List<User> findUserList(PageBean pageBean);
}
