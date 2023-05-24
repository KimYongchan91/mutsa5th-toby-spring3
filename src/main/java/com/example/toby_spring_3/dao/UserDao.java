package com.example.toby_spring_3.dao;

import com.example.toby_spring_3.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class UserDao {
    ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into user(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST"); //DB_HOST=jdbc:mysql://localhost:3306/spring-db
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost, dbUser, dbPassword
        );

        PreparedStatement pstmt = conn.prepareStatement("select id, name, password from user where id = ?");
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
}