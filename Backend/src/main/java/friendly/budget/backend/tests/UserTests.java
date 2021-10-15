package friendly.budget.backend.tests;

import friendly.budget.backend.DAO.UserDAO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;

@Component
public class UserTests {
    public UserTests() throws InvocationTargetException, IllegalAccessException, InstantiationException {
    }

    private JdbcTemplate jdbcTemplate = jdbcTemplate();

    @Bean
    JdbcTemplate jdbcTemplate() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        final String driverClassName = "com.mysql.cj.jdbc.Driver";
        final String jdbcUrl = "jdbc:mysql://localhost:3306/friendlyBudget";
        final String username = "admin";
        final String password = "admin";
        final Class<?> driverClass = ClassUtils.resolveClassName(driverClassName, this.getClass().getClassLoader());
        final Driver driver = (Driver) ClassUtils.getConstructorIfAvailable(driverClass).newInstance();
        final DataSource dataSource = new SimpleDriverDataSource(driver, jdbcUrl, username, password);
        return new JdbcTemplate(dataSource);
    }


    @Test
    public void LoginUserTest(){

        //Defining the values for the tests
        String username = "Joaozinho";
        UserDAO userDAO = new UserDAO();

        //Insert user on DB manually (the sign in was not implemented yet)
        jdbcTemplate.update("insert into USERS (NAME) values (?);",username);

        //Consulting user on DB (login operation)
        Assert.assertEquals(username, userDAO.login(username,jdbcTemplate).getName());
    }
}
