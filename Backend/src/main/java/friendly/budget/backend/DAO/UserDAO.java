package friendly.budget.backend.DAO;

import friendly.budget.backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class  UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static User mapUserRow(ResultSet rs, int rowNum) {
        try {
            return new User(rs.getString("NAME"));
        } catch (SQLException e) {
            throw new RuntimeException("Failure to map db row to Transaction", e);
        }
    }

    public User login (String username) {
        final List<User> query = jdbcTemplate.query("select NAME from USERS where NAME = (?);"
                , UserDAO::mapUserRow, username);
        if (query.isEmpty()) return null;
        return query.get(0);
    }

}
