package friendly.budget.backend.DAO;

import friendly.budget.backend.models.Transaction;
import friendly.budget.backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static User login (String username){

    }

    public void insert (Transaction transaction) {
        //insert the transaction into the DB
        jdbcTemplate.update("insert into TRANSACTIONS (NAME) values (?);", transaction.getUser());
    }


}
