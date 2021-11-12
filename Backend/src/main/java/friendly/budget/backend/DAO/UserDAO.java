package friendly.budget.backend.DAO;

import friendly.budget.backend.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//@Controller
public class  UserDAO {


    private static User mapUserRow(ResultSet rs, int rowNum) {
        try {
            return new User(rs.getString("NAME"), rs.getString("PASSWORD"));
        } catch (SQLException e) {
            throw new RuntimeException("Failure to map db row to Transaction", e);
        }
    }
    //@PostMapping(path="/login")
    public /*@ResponseBody*/ User login (User user, JdbcTemplate jdbcTemplate) {
        final List<User> query = jdbcTemplate.query("select NAME,PASSWORD from USERS where NAME = (?) and PASSWORD = (?);"
                , UserDAO::mapUserRow, user.getName(), user.getPassword());
        if (query.isEmpty()) return null;
        else{return query.get(0);}
    }

    public boolean addUser(User user, JdbcTemplate jdbcTemplate){
        final List<User> query = jdbcTemplate.query("select NAME from USERS where NAME = (?);"
            , UserDAO::mapUserRow, user.getName());
        if (query.isEmpty()) {
            jdbcTemplate.update("insert into USERS (NAME,PASSWORD) values (?,?);", user.getName(), user.getPassword());
            return true;
        }
        return false;
    }
}
