package friendly.budget.backend.DAO;

import friendly.budget.backend.models.Transaction;
import friendly.budget.backend.models.TransactionDTO;
import friendly.budget.backend.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class TransactionDAO {

    /**
     * Funtion that models the return from the list() function
     *
     **/
    private static Transaction mapTransactionRow(ResultSet rs, int rowNum) {
        try {
            return new Transaction(
                    new User(rs.getString("NAME")),
                    rs.getFloat("VALUE"),
                    rs.getString("DATE"),
                    rs.getString("DESCRIPTION"));
        } catch (SQLException e) {
            throw new RuntimeException("Failure to map db row to Transaction", e);
        }
    }

    /**
     * Insert the transaction into the DB
     * 
     * @param transaction The inserting transaction
     * */
    //@PostMapping(path="/add")
    public List<Transaction> insert (Transaction transaction, User user, JdbcTemplate jdbcTemplate) {

        jdbcTemplate.update(
                "insert into TRANSACTIONS (NAME, DATE, VALUE, DESCRIPTION) values (?,?,?,?);",
                transaction.getUser().getName(), transaction.getDate(),transaction.getValue(),
                transaction.getDescription());

        return (new TransactionDAO()).list (user, jdbcTemplate);
    }

    /**
     * Function that return the values from DB
     * 
     * @return Data from DB
     **/

    public List<Transaction> list (User user, JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.query("select NAME,DATE,VALUE,DESCRIPTION from TRANSACTIONS where NAME = (?);",
                TransactionDAO::mapTransactionRow,user.getName());
    }

}
