package kr.ac.jejunu.userdao;

public class Daofactory {
    public UserDao userdao() {
        return new UserDao(connection_maker());
    }

    public Connection_maker connection_maker() {
        return new Jeju_connection_maker();
    }
}
