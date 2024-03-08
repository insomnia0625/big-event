package com.itheima.service;

import com.itheima.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);
}
