package com.example.zookeeper.repository;

import com.example.zookeeper.dao.User;
import com.example.zookeeper.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getByUserNameAndUserPassword(String userName, String userPassword) {
        return userMapper.selectByUserNameAndPassword(userName, userPassword);
    }

    @Override
    public User getByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public void insert(String userName, String userPassword, String userEmail) {
        userMapper.insert(userName, userPassword, userEmail);
    }
}
