package hmy.webapp.dao;

import hmy.webapp.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    //@Select("select * from user where id = #{id}")
    User selectUserById(String id);

    //@Select("select * from user where username = #{username}")
    User selectUserByUsername(String username);

    //@Select("select * from user where username = #{username} and password = #{password}")
    User selectUserByUsernameAndPassword(String username, String password);

    //@Insert("insert into user(username, password) values(#{username}, #{password})")
    boolean insertUser(User user);

    //@Update("update user set username = #{username}, password = #{password} where id = #{id}")
    boolean updateUser(User user);

    //@Delete("delete from user where id = #{id}")
    boolean deleteUserById(int id);

    //test api
    List<User> selectAllUsers();
}