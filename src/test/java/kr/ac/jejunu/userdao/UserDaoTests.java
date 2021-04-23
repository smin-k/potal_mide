package kr.ac.jejunu.userdao;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    static UserDao userDao;
    @BeforeAll
    static void setup(){
        ApplicationContext context = new AnnotationConfigApplicationContext(Daofactory.class);
        userDao = context.getBean("userdao",
                UserDao.class);
    }

    @Test
    public void Get() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "hulk";
        String password = "1234";
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "hulk";
        user.setPassword( "1234");
        user.setName(name);

        userDao.insert(user);

        user = userDao.get(user.getId());
        assertThat(user.getName(), is(name));

    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setPassword( "1234");
        user.setName("hulk");
        userDao.insert(user);

        userDao.delete(user.getId());
        User deleted_user = userDao.get(user.getId());
        assertThat(deleted_user, IsNull.nullValue());
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        String name = "hulk";
        String password = "1234";
        User user = new User();
        user.setPassword(password);
        user.setName(name);
        userDao.insert(user);

        String update_name = "hulk";
        String update_password = "1234";
        user.setName( update_name);
        user.setPassword(update_password);
        userDao.update(user);
        User updated_user = userDao.get(user.getId());
        assertThat(user.getName(), is(name));

    }

}