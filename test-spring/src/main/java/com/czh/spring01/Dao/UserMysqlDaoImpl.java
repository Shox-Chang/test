package com.czh.spring01.Dao;

public class UserMysqlDaoImpl implements UserDao{
    @Override
    public void queryUser() {
        System.out.println("mysql查询");
    }
}
