package com.example.toby_spring_3.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * packageName : com.example.springbootgradle.dao
 * fileName : ConnectionMaker
 * author : gim-yeong-geun
 * date : 2023/05/24
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/24         gim-yeong-geun          최초 생성
 */
public interface ConnectionMaker {
    Connection makeConnection() throws ClassNotFoundException, SQLException;
}