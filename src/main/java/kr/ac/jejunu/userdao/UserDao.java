package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {

    private final Jdbc_context jdbc_context ;

    UserDao(Jdbc_context jdbc_context){
        this.jdbc_context = jdbc_context;
    }

    public User get(Integer id) throws SQLException {
        Object[] arr = {id};
        String sql = "select * from userinfo where id = ?";
        return jdbc_context.get(arr, sql);
    }

    public User insert(User user) throws SQLException{
        String sql = "insert into userinfo (name, password)  values (?,?)";
        Object[] arr = {user.getName(), user.getPassword()};
        return jdbc_context.insert(user, sql, arr, this);
    }

    public void delete(Integer id) throws SQLException {
        Object[] arr = {id};
        String sql = "delete from userinfo where id = ? ";
        jdbc_context.up_del(arr, sql);
    }

    public void update(User user) throws SQLException {
        Object[] arr = {user.getName(),user.getPassword(),user.getId()};
        String sql = "update userinfo set name = ? , password = ? where id = ?";
        jdbc_context.up_del(arr, sql);
    }

}