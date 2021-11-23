package com.example.zookeeper.repository;

import com.example.zookeeper.dao.User;

public interface UserRepository {
    User getByUserNameAndUserPassword(String userName, String userPassword);

    User getByUserName(String userName);

    void insert(String userName, String userPassword, String userEmail);
}
