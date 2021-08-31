package friendly.budget.backend.resource;

import friendly.budget.backend.DAO.TransactionDAO;
import friendly.budget.backend.DAO.UserDAO;
import friendly.budget.backend.models.Transaction;
import friendly.budget.backend.models.TransactionDTO;
import friendly.budget.backend.models.User;
import friendly.budget.backend.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import java.util.List;

@Controller
public class Requests {

    private User user;
    private final TransactionDAO transactionDAO = new TransactionDAO();
    private final UserDAO userDAO = new UserDAO();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @CrossOrigin(origins = "http://localhost:8080/")
    @PostMapping(path="/login")
    public @ResponseBody boolean login(@RequestBody final String name) {
        final User notAuthenticatedUser = userDAO.login(name,jdbcTemplate);
        if (notAuthenticatedUser != null){
            this.user = notAuthenticatedUser;
            return true;
        }
        return false;
    }

    //@PUT
    @CrossOrigin(origins = "http://localhost:8080/")
    @PutMapping(path="/add")
    public @ResponseBody String add(@RequestBody final String json) {
        final TransactionDTO transactionDTO = JsonUtil.fromJson(json, TransactionDTO.class);
        return JsonUtil.toJson( transactionDAO.insert(new Transaction(this.user, transactionDTO.getValue(), transactionDTO.getDate(),transactionDTO.getDescription()) , this.user, jdbcTemplate) );
    }

    @CrossOrigin(origins = "http://localhost:8080/")
    @GetMapping(path="/transactions")
    public @ResponseBody String getTransactions() {
        List<Transaction> list = (new TransactionDAO()).list(this.user,jdbcTemplate);
        return JsonUtil.toJson(list);

        //if (list==null) return "";
        //ArrayList<Transaction> arrayList = new ArrayList<Transaction>(list);
    }

    @GetMapping(path="/test")
    public @ResponseBody String test() {
        return "NICE TRY";
    }
}