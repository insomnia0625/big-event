package com.itheima.service.impl;

import com.itheima.entity.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.UserService;
import com.itheima.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        // 使用Md5Util工具类进行密码加密
        String Md5String = Md5Util.getMD5String(password);
        // 添加
        userMapper.addUser(username, Md5String);
    }
}
