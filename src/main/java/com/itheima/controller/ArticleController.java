package com.itheima.controller;

import com.itheima.entity.Result;
import com.itheima.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(){
//        // 验证token
//        try {
//            Map<String,Object> claims = JwtUtil.parseToken(token);
//            return  Result.success("文章列表");
//        }catch (Exception e){
//            // http响应状态码为401
//            response.setStatus(401);
//            return Result.error("用户未登录");
//        }
        return  Result.success("文章列表");
    }
}
