package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Get_Statement_Maker implements Statement_Maker {
    Integer id;
    public Get_Statement_Maker(Integer id) {
        this.id = id ;
    }

    @Override
    public PreparedStatement make_statement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }
}
