package com.example.zookeeper.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.zookeeper.dao.User;
import com.example.zookeeper.repository.UserRepository;
import com.example.zookeeper.util.StringUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
    @Resource
    private UserRepository userRepository;

    @GetMapping("login")
    public JSONObject login(@RequestParam String userName,
                            @RequestParam String userPassword) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(userPassword)) {
            response.put("code", 500);
            response.put("isLogin", false);
            response.put("message", "传参缺失");
            return response;
        }
        User user = userRepository.getByUserName(userName);
        if (user == null) {
            response.put("code", 404);
            response.put("isLogin", false);
            response.put("message", "用户名不存在");
            return response;
        }
        response.put("code", 200);
        boolean isLogin = user.getUserPassword().equals(userPassword);
        response.put("isLogin", isLogin);
        response.put("message", isLogin ? "登陆成功" : "密码错误");
        return response;
    }


    @GetMapping("register")
    public JSONObject register(@RequestParam String userName,
                               @RequestParam String userPassword,
                               @RequestParam String userEmail) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(userPassword)) {
            response.put("code", 500);
            response.put("successfullyRegister", false);
            response.put("message", "传参缺失");
            return response;
        }
        if (!StringUtil.isValidEmail(userEmail)) {
            response.put("code", 500);
            response.put("successfullyRegister", false);
            response.put("message", "不合法邮箱");
            return response;
        }
        User user = userRepository.getByUserName(userName);
        if (user != null) {
            response.put("code", 400);
            response.put("successfullyRegister", false);
            response.put("message", "用户名已经存在");
            return response;
        }
        userRepository.insert(userName, userPassword, userEmail);
        response.put("code", 200);
        response.put("successfullyRegister", true);
        response.put("message", "注册成功");
        return response;
    }

}
