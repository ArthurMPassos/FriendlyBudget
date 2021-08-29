package friendly.budget.backend.DAO;

import friendly.budget.backend.models.Transaction;
import friendly.budget.backend.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class TransactionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    @PostMapping(path="/add")
    public @ResponseBody List<Transaction> insert (Transaction transaction, User user) {

        jdbcTemplate.update(
                "insert into TRANSACTIONS (NAME, DATE, VALUE, DESCRIPTION) values (?,?,?,?);",
                transaction.getUser().getName(), transaction.getDate(),transaction.getValue(),
                transaction.getDescription());

        return (new TransactionDAO()).list (user);
    }

    /**
     * Function that return the values from DB
     * 
     * @return Data from DB
     **/
    @GetMapping(path="/transactions")
    public @ResponseBody List<Transaction> list (User user) {
        return jdbcTemplate.query("select DATE,VALUE,DESCRIPTION from TRANSACTIONS where NAME = (?);",
                TransactionDAO::mapTransactionRow,user.getName());
    }

}
