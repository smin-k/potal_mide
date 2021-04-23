package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private final Jdbc_context jdbc_context ;

    UserDao(Jdbc_context jdbc_context){
        this.jdbc_context = jdbc_context;
    }

    public User get(Integer id) throws SQLException {
        Statement_Maker statement_maker = new Get_Statement_Maker(id);
        return jdbc_context.get_context(statement_maker);
    }

    public User insert(User user) throws SQLException{
        Statement_Maker statement_maker = new Insert_Statement_Maker(user);
        return jdbc_context.insert_context(user, statement_maker);

    }

    public void delete(Integer id) throws SQLException {
        Statement_Maker statement_maker = new Delete_Statement_Maker(id);
        jdbc_context.updel_context(statement_maker);
        //리턴
    }

    public void update(User user) throws SQLException {
        Statement_Maker statement_maker = new Update_Statement_Maker(user);
        jdbc_context.updel_context(statement_maker);
        //리턴
    }

    private User insert_context(User user, Statement_Maker statement_maker) throws SQLException {
        //리턴
        return jdbc_context.insert_context(user, statement_maker);
    }

    private User get_context(Statement_Maker statement_maker) throws SQLException {

        //리턴
        return jdbc_context.get_context(statement_maker);
    }

    private void updel_context(Statement_Maker statement_maker) throws SQLException {
        jdbc_context.updel_context(statement_maker);
    }
}