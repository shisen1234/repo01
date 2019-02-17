package com.itheima.service.impl;


import com.itheima.domain.PageBean;
import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user) throws Exception {
        return userMapper.login(user);
    }
}*/

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user)  {
        return userMapper.login(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public void delete(int id) {
        userMapper.delete(id);
    }

    @Override
    public User findUser(int id) {
        return userMapper.findUser(id);
    }

    @Override
    public Integer findTotalCount() {

        return userMapper.findTotalCount();
    }

    @Override
    public List<User> findUserList(PageBean pageBean) {

        return userMapper.findUserList(pageBean);

    }

}
