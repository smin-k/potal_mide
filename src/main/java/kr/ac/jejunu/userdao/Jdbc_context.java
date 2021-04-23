package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class Jdbc_context {
    private final DataSource dataSource;

    public Jdbc_context(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    User insert_context(User user, Statement_Maker statement_maker) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statement_maker.make_statement(connection);

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //리턴
        return user;
    }

    User get_context(Statement_Maker statement_maker) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statement_maker.make_statement(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }

        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //리턴
        return user;
    }

    void updel_context(Statement_Maker statement_maker) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statement_maker.make_statement(connection);
            preparedStatement.executeUpdate();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    User get(Object[] arr, String sql) throws SQLException {
        return get_context(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < arr.length ; i ++ ){
                preparedStatement.setObject(i+1, arr[i]);
            }
            return preparedStatement;
        });
    }

    User insert(User user, String sql, Object[] arr, UserDao userDao) throws SQLException {
        return insert_context(user, connection->{
            PreparedStatement preparedStatement = connection.prepareStatement(sql
                    , Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < arr.length ; i ++ ){
                preparedStatement.setObject(i+1, arr[i]);
            }
            return preparedStatement;
        });
    }

    void up_del(Object[] arr, String sql) throws SQLException {
        updel_context(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < arr.length; i++) {
                preparedStatement.setObject(i + 1, arr[i]);
            }
            return preparedStatement;
        });
    }
}