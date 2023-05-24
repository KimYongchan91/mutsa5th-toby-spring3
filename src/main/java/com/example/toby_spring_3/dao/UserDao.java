package com.example.toby_spring_3.dao;

import com.example.toby_spring_3.domain.User;

import java.sql.*;

public abstract class UserDao {
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

    SimpleConnectionMaker connectionMaker = new SimpleConnectionMaker();

    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection conn = connectionMaker.makeNewConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeNewConnection();

        PreparedStatement pstmt = conn.prepareStatement("select id, name, password from users where id = ?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next(); // ctrl + enter

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        pstmt.close();
        conn.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao() {
            @Override
            public Connection getConnection() throws ClassNotFoundException, SQLException {
                return null;
            }
        };
        User user = new User();
        user.setId("6");
        user.setName("kyo");
        user.setPassword("1234");
        userDao.add(user);

        User selectedUser = userDao.get("6");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());
    }


}