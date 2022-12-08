package com.itszb.service;

import com.itszb.mappers.UserMapper;
import com.itszb.pojo.User;
import com.itszb.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @description:
 * @author: wuchao
 * @date: 2022/10/21 14:24
 * @version: 1.0
 */

public class UserService {
    //1.使用工具类获取SqlSessionFactory
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 登录方法
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password){
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4. 调用方法
        User user = mapper.select(username, password);
        //释放资源
        sqlSession.close();

        return  user;
    }

    /**
     * 注册方法
     * @param user
     * @return
     */
    public boolean register(User user){
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4. 调用方法判断用户名是否存在
        User u = mapper.selectByUsername(user.getUsername());
        if (u == null) {
            //用户名不存在,注册
            mapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();
        return u == null;
    }
}
