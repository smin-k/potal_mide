package kr.ac.jejunu.userdao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Daofactory {

    @Bean
    public UserDao userdao() {
        return new UserDao(connection_maker());
    }

    @Bean
    public Connection_maker connection_maker() {
        return new Jeju_connection_maker();
    }
}
