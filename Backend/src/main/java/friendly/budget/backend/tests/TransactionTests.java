package friendly.budget.backend.tests;

import friendly.budget.backend.DAO.TransactionDAO;
import friendly.budget.backend.models.Transaction;
import friendly.budget.backend.models.User;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.util.ClassUtils;
import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;
import java.util.List;


public class TransactionTests {
    public TransactionTests() throws InvocationTargetException, IllegalAccessException, InstantiationException {
    }

    private JdbcTemplate jdbcTemplate = jdbcTemplate();

    @Bean
    JdbcTemplate jdbcTemplate() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        final String driverClassName = "com.mysql.cj.jdbc.Driver";
        final String jdbcUrl = "jdbc:mysql://localhost:3306/friendlybudget";
        final String username = "admin";
        final String password = "admin";
        final Class<?> driverClass = ClassUtils.resolveClassName(driverClassName, this.getClass().getClassLoader());
        final Driver driver = (Driver) ClassUtils.getConstructorIfAvailable(driverClass).newInstance();
        final DataSource dataSource = new SimpleDriverDataSource(driver, jdbcUrl, username, password);
        return new JdbcTemplate(dataSource);
    }


    @Test
    public void InsertTransactionTest(){

        //Defining the values for the tests
        User user = new User("Gabriel"); //Login
        TransactionDAO transactionDAO = new TransactionDAO();
        List<Transaction> transactionList;

        //Insert transaction on DB (insert function)
        Transaction transaction = new Transaction(user, (float) 2.99, "01/10/2021", "Lapis");
        transactionDAO.insert(transaction, user, jdbcTemplate);

        //Consults transaction on DB (list function)
        transactionList = transactionDAO.list(user,jdbcTemplate);
        int indexLast = transactionList.size() - 1;
        Assert.assertEquals(transaction.getUser().getName(), transactionList.get(indexLast).getUser().getName());
        Assert.assertEquals(transaction.getValue(), transactionList.get(indexLast).getValue(),2);
        Assert.assertEquals(transaction.getDate(), transactionList.get(indexLast).getDate());
        Assert.assertEquals(transaction.getDescription(), transactionList.get(indexLast).getDescription());
    }
}
