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
import java.util.LinkedList;
import java.util.List;

@Controller
public class Requests {

    public static final String ORIGIN_ADDRESS = "http://localhost:8080/";

    private User user;
    private final TransactionDAO transactionDAO = new TransactionDAO();
    private final UserDAO userDAO = new UserDAO();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @CrossOrigin(origins = ORIGIN_ADDRESS)
    @PostMapping(path="/login")
    public @ResponseBody boolean login(@RequestBody final String json) {
        final User user = JsonUtil.fromJson(json, User.class);
        final User notAuthenticatedUser = userDAO.login(user,jdbcTemplate);
        if (notAuthenticatedUser != null){
            this.user = notAuthenticatedUser;
            return true;
        }
        this.user = null;
        return false;
    }

    //@PUT
    @CrossOrigin(origins = ORIGIN_ADDRESS)
    @PutMapping(path="/add")
    public @ResponseBody String add(@RequestBody final String json) {
        final TransactionDTO transactionDTO = JsonUtil.fromJson(json, TransactionDTO.class);
        List<Transaction> rInsert = transactionDAO.insert(new Transaction(this.user.getName(), transactionDTO.getValue(),
                transactionDTO.getDate(), transactionDTO.getDescription()), this.user, jdbcTemplate);

        List<TransactionDTO> listTransDTO = new LinkedList<>();
        for (Transaction transaction: rInsert) {
            listTransDTO.add(new TransactionDTO(transaction));
        }
        return JsonUtil.toJson(listTransDTO);
    }

    @CrossOrigin(origins = ORIGIN_ADDRESS)
    @GetMapping(path="/transactions")
    public @ResponseBody String getTransactions() {
        List<Transaction> list = (new TransactionDAO()).list(this.user,jdbcTemplate);

        List<TransactionDTO> listTransDTO = new LinkedList<>();
        for (Transaction transaction: list) {
            listTransDTO.add(new TransactionDTO(transaction));
        }

        return JsonUtil.toJson(listTransDTO);
    }

    @CrossOrigin(origins = ORIGIN_ADDRESS)
    @PutMapping(path="/createUser")
    public @ResponseBody String createUser(@RequestBody final String json) {
        final User user = JsonUtil.fromJson(json, User.class);
        userDAO.addUser(user, jdbcTemplate);
        return "created";
    }

    @GetMapping(path="/test")
    public @ResponseBody String test() {
        return "NICE TRY";
    }
}