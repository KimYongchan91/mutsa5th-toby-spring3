package com.example.toby_spring_3.dao;

import com.example.toby_spring_3.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        User user = new User();
        user.setId("16");
        user.setName("kyeongrok");
        user.setPassword("1234");
        userDao.add(user);

        User selectedUser = userDao.get("16");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());
    }
}