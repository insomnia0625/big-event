package com.itheima.controller;

import com.itheima.entity.Result;
import com.itheima.entity.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        // Pattern参数校验，为5~16位的非空字符串,不满足则由全局异常处理器捕获，返回Result对象
        // 查询是否存在
        User u = userService.findByUserName(username);
        if(u == null){
            userService.register(username, password);
            return Result.success();
        }else{
            return Result.error("该用户名已存在");
        }
        // 完成注册
    }

    @PostMapping("/login")
    public Result<String> login(String username, String password){
        // 查询用户是否存在
        User LoginUser = userService.findByUserName(username);
        if(LoginUser == null){
            return Result.error("用户名错误");
        }
        // 校验密码
        if(Md5Util.getMD5String(password).equals(LoginUser.getPassword())){
            Map<String,Object> claim = new HashMap<>();
            claim.put("id", LoginUser.getId());
            claim.put("username", LoginUser.getUsername());
            String token = JwtUtil.genToken(claim);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }
}
