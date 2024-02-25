package hmy.webapp;

import hmy.webapp.dao.UserDao;
import hmy.webapp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class UserDaoTestCase {

    @Autowired
    private UserDao userDao;

    @Test
    public void testUser() {
        List<User> userList = userDao.selectAllUsers();
        userList.forEach(user -> {
            System.out.println("ID：" + user.getId() + ", Name：" + user.getUsername());
        });
    }

    @Test
    void selectUserById() throws IOException {
//        // 1.读取配置文件
//        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
//        // 2.创建 SqlSessionFactory 工厂
//        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
//        // 3.获取 SqlSession 对象
//        SqlSession session = factory.openSession();
//        // 4.使用 SqlSession 创建 Dao 接口的代理对象
//        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.selectUserById("123");
        System.out.println(user);
    }

    @Test
    void insertUser() {
        User user = new User("test1", "test2");
        boolean result = userDao.insertUser(user);
        System.out.println(result);
    }

    @Test
    void updateUserById() {
        User user = new User(1, "test3", "test4");
        user.setId(1);
        boolean result = userDao.updateUser(user);
        System.out.println(result);
    }

    @Test
    void deleteUserById() {
        boolean result = userDao.deleteUserById(2);
        System.out.println(result);
    }
}
