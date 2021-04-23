package kr.ac.jejunu.userdao;

import java.sql.*;

abstract public class UserDao {
    public User get(Integer id) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();
        //리턴
        return user;
    }

    public User insert(User user) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("insert into userinfo (name, password)  values (?,?)"
                        ,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1, user.getName());
        preparedStatement.setObject(2, user.getPassword());
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        user.setId(resultSet.getInt(1));


        resultSet.close();
        preparedStatement.close();
        connection.close();
        //리턴
        return user;

    }

    abstract Connection getConnection() throws ClassNotFoundException, SQLException;
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        return DriverManager.getConnection("jdbc:mysql://localhost/jeju?serverTimezone=UTC"
//                , "root", "5344");
//    }
}