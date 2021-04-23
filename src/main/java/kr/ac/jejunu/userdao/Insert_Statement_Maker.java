package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert_Statement_Maker implements Statement_Maker {
    User user;
    public Insert_Statement_Maker(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement make_statement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo (name, password)  values (?,?)"
                , Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1, user.getName());
        preparedStatement.setObject(2, user.getPassword());
        return preparedStatement;
    }
}
