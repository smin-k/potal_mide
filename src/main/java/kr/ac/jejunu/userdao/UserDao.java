package kr.ac.jejunu.userdao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;

public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    UserDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public User get(Integer id){
        String sql = "select * from userinfo where id = ?";
        return jdbcTemplate.query(sql, rs -> {
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        }, id);
    }

    public User insert(User user){
        String sql = "insert into userinfo (name, password)  values (?,?)";
        Object[] arr = {user.getName(), user.getPassword()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql
                    , Statement.RETURN_GENERATED_KEYS
            );
            for (int i = 0; i < arr.length; i++) {
                preparedStatement.setObject(i + 1, arr[i]);
            }
            return preparedStatement;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
        return user;
    }

    public void delete(Integer id){
        Object[] arr = {id};
        String sql = "delete from userinfo where id = ? ";
        jdbcTemplate.update(sql,arr);
    }

    public void update(User user){
        Object[] arr = {user.getName(),user.getPassword(),user.getId()};
        String sql = "update userinfo set name = ? , password = ? where id = ?";
        jdbcTemplate.update(sql,arr);
    }

}