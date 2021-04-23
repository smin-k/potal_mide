package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface Connection_maker {
    abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}