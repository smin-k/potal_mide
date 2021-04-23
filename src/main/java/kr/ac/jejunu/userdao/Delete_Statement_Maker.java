package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete_Statement_Maker implements Statement_Maker {
    Integer id;
    public Delete_Statement_Maker(Integer id) {
        this.id = id;
    }
    @Override
    public PreparedStatement make_statement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from userinfo where id = ? ");
        preparedStatement.setObject(1, id);
        return preparedStatement;
    }
}
