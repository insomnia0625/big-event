package com.itheima.mapper;

import com.itheima.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    @Select("insert into user(username,password,create_time,update_time)" +
            "values(#{username},#{password},now(),now())")
    void addUser(String username, String password);
}
