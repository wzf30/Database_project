package com.example.zookeeper.mapper;

import com.example.zookeeper.dao.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectByUserNameAndPassword(@Param("userName") String userName,
                                     @Param("userPassword") String userPassword);

    User selectByUserName(@Param("userName") String userName);

    void insert(@Param("userName") String userName,
                @Param("userPassword") String userPassword,
                @Param("userEmail") String userEmail);
}
