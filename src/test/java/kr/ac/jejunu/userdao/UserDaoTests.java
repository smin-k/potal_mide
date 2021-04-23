package kr.ac.jejunu.userdao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    @Test
    public void Get() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "hulk";
        String password = "1234";
        UserDao userDao = new Jeju_UserDao();
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void halla_Get() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "hulk";
        String password = "1234";
        UserDao userDao = new halla_UserDao();
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException { ;
        String name = "hulk";
        String password = "1234";
        User user = new User();

        user.setPassword( "1234");
        user.setName("hulk");

        UserDao userDao = new Jeju_UserDao();
        userDao.insert(user);

        user = userDao.get(user.getId());
        assertThat(user.getName(), is(name));

    }

    @Test
    public void halla_insert() throws SQLException, ClassNotFoundException { ;
        String name = "hulk";
        String password = "1234";
        User user = new User();

        user.setPassword( "1234");
        user.setName("hulk");

        UserDao userDao = new halla_UserDao();
        userDao.insert(user);

        user = userDao.get(user.getId());
        assertThat(user.getName(), is(name));

    }
}