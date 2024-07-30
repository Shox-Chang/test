package com.czh.spring01.service;

import com.czh.spring01.Dao.UserDao;
import com.czh.spring01.Dao.UserDaoImpl;
import com.czh.spring01.Dao.UserMysqlDaoImpl;

public class UserServiceImpl implements UserService{

//    private UserDao userDao = new UserMysqlDaoImpl();

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void queryUser() {
        userDao.queryUser();
    }
}
