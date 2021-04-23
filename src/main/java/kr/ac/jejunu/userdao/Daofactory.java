package kr.ac.jejunu.userdao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class Daofactory {
    @Value("${db.driver}")
    String driver;
    @Value("${db.url}")
    String url;
    @Value("${db.password}")
    String password;
    @Value("${db.name}")
    String name;


    @Bean
    public UserDao userdao() throws ClassNotFoundException {
        return new UserDao(jdbc_context());
    }

    @Bean
    public Jdbc_context jdbc_context() throws ClassNotFoundException {
        return new Jdbc_context(dataSource());
    }

    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        Class<? extends Driver> aClass = (Class<? extends Driver>) Class.forName(driver);

        dataSource.setDriverClass(aClass);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setUsername(name);
        return dataSource;

    }

}
