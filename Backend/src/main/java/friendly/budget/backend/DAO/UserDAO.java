package friendly.budget.backend.DAO;

import friendly.budget.backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
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
    @PostMapping(path="/login")
    public @ResponseBody Boolean login (@RequestBody String username) {
        final List<User> query = jdbcTemplate.query("select NAME from USERS where NAME = (?);"
                , UserDAO::mapUserRow, username);
        if (query.isEmpty()) return false;
        else{return true;}
    }

}
