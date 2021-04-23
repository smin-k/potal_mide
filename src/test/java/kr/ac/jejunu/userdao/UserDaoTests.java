package kr.ac.jejunu.userdao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    @Test
    public void Get() throws SQLException, ClassNotFoundException {
        Connection_maker connection_maker = new Jeju_connection_maker();
        Integer id = 1;
        String name = "hulk";
        String password = "1234";
        UserDao userDao = new UserDao(connection_maker);
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        Connection_maker connection_maker = new Jeju_connection_maker();
        String name = "hulk";
        String password = "1234";
        User user = new User();

        user.setPassword( "1234");
        user.setName("hulk");

        UserDao userDao =new UserDao(connection_maker);
        userDao.insert(user);

        user = userDao.get(user.getId());
        assertThat(user.getName(), is(name));

    }

}