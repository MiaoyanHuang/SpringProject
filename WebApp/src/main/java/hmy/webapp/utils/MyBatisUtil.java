package hmy.webapp.utils;

import hmy.webapp.exception.BaseException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * MyBatis 工具类
 * 废弃原因：采用yml配置文件，不再需要此类
 */
@Deprecated
public class MyBatisUtil {

    // 定义 SqlSessionFactory
    private static final SqlSessionFactory factory;

    // 使用静态块只创建一次 SqlSessionFactory
    static {
        try {
            // 读取配置文件
            InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
            // 创建 SqlSessionFactory 对象
            factory = new SqlSessionFactoryBuilder().build(in);
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        }
    }

    // 获取 SqlSession 对象
    public static SqlSession getSqlSession() {
        // 自动提交事务 只需要传入一个参数 true
        // SqlSession sqlSession = factory.openSession(true);
        return factory.openSession();
    }

    // 提交事务
    public static void commit(SqlSession sqlSession) {
        if (null != sqlSession) {
            sqlSession.commit();
        }
        close();
    }

    // 回滚事务
    public static void rollBack(SqlSession sqlSession) {
        if (null != sqlSession) {
            sqlSession.rollback();
        }
        close();
    }

    // 关闭 SqlSession
    public static void close() {

        SqlSession sqlSession = getSqlSession();
        if (null != sqlSession) {
            sqlSession.close();
        }
    }
}