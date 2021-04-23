package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Statement_Maker {
    abstract PreparedStatement make_statement(Connection connection) throws SQLException;
}
