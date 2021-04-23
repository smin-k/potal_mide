package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update_Statement_Maker implements Statement_Maker {
    User user;

    Update_Statement_Maker(User user){
        this.user = user;
    }
    @Override
    public PreparedStatement make_statement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update userinfo set name = ? , password = ? where id = ?");
        preparedStatement.setObject(1, user.getName());
        preparedStatement.setObject(2, user.getPassword());
        preparedStatement.setObject(3, user.getId());

        return preparedStatement;
    }
}
