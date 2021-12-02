package com.lagou.test;

import com.lagou.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author xyf
 * @date 2021/10/30 14:59
 * @description
 */
public class MybatisTest {

    //快速入门测试方法
    @Test
    public void mybatisQuickStart() throws IOException {
        //1.加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.获取sqlSession工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.执行sql  参数：namespace.id
        List<User> users = sqlSession.selectList("UserMapper.findAll");
        //5.遍历打印结果
        for (User user : users) {
            System.out.println(user);
        }
        //6.关闭资源
        sqlSession.close();
    }

    //测试新增用户
    @Test
    public void testSaveUser() throws IOException {
        // 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 获取SqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获取SqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行sql
        User user = new User();
        user.setUsername("jack");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京海淀");
        int i = sqlSession.insert("UserMapper.saveUser", user);
        System.out.println(i);
        // DML语句，手动提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //测试更新用户
    @Test
    public void testUpdateUser() throws IOException {
        // 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 获取SqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获取SqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行sql
        User user = new User();
        user.setId(7);
        user.setUsername("luck");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("北京朝阳");
        int i = sqlSession.update("UserMapper.updateUser", user);
        System.out.println(i);
        // DML语句，手动提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //测试删除
    @Test
    public void testDeleteUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int i = sqlSession.delete("UserMapper.deleteUser", 4);
        sqlSession.commit();
        sqlSession.close();
    }


}
