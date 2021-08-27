package friendly.budget.backend.DAO;

import friendly.budget.backend.model.datafile.DataFile;
import friendly.budget.backend.models.Transaction;
import friendly.budget.backend.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransactionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * Funtion that models the return from the list() function
     *
     **/
    private static Transaction mapTransactionRow(ResultSet rs, int rowNum) {
        try {
            return new Transaction(new User(rs.getString("NAME")),rs.getFloat("VALUE"),rs.getString("DATE"),rs.getString("DESCRIPTION"));
        } catch (SQLException e) {
            throw new RuntimeException("Failure to map db row to Transaction", e);
        }
    }

    /**
     * Insert the transaction into the DB
     * 
     * @param transaction The inserting transaction
     * */
    public void insert (Transaction transaction) {

        jdbcTemplate.update("insert into TRANSACTIONS (NAME, DATE, VALUE, DESCRIPTION) values (?,?,?,?);", transaction.getUser().getName(), transaction.getDate(),transaction.getValue(), transaction.getDescription());
    }

    /**
     * Function that return the values from DB
     * 
     * @return Data from DB
     **/
    public List<Transaction> list (User user) {
        final List<Transaction> resultList = jdbcTemplate.query("select DATE,VALUE,DESCRIPTION from USERS where NAME = (?);", TransactionDAO::mapTransactionRow,user.getName());
        if(resultList.isEmpty()){
            return null;
        }
        return resultList;
    }





}
