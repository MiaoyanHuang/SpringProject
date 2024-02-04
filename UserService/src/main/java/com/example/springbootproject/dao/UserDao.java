package com.example.springbootproject.dao;

import com.example.springbootproject.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    User selectUserById(int id);

    @Select("select * from user where username = #{username} and password = #{password}")
    User selectUserByUsernameAndPassword(String username, String password);

    @Insert("insert into user(username, password) values(#{username}, #{password})")
    boolean insertUser(User user);

    @Update("update user set username = #{username}, password = #{password} where id = #{id}")
    boolean updateUser(User user);

    @Delete("delete from user where id = #{id}")
    boolean deleteUserById(int id);
}
