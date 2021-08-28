package friendly.budget.backend.resource;

import friendly.budget.backend.DAO.TransactionDAO;
import friendly.budget.backend.DAO.UserDAO;
import friendly.budget.backend.models.Transaction;
import friendly.budget.backend.models.User;
import friendly.budget.backend.util.JsonUtil;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import java.util.List;

@Path("")
public class Requests {

    private User user;
    private final TransactionDAO transactionDAO = new TransactionDAO();
    private final UserDAO userDAO = new UserDAO();


    @POST
    @Path("/login")
    public boolean login(@RequestBody final String name) {
        final User notAuthenticatedUser = userDAO.login(name);
        if (notAuthenticatedUser != null){
            this.user = notAuthenticatedUser;
            return true;
        }
        return false;
    }

    @PUT
    @Path("/add")
    public String add(@RequestBody final String json) {
        final Transaction newTransaction = JsonUtil.fromJson(json, Transaction.class);


        return JsonUtil.toJson( transactionDAO.insert( newTransaction, this.user ) );
    }

    @GET
    @Path("/transactions")
    public String getTransactions() {
        List<Transaction> list = (new TransactionDAO()).list(this.user);
        return JsonUtil.toJson(list);

        //if (list==null) return "";
        //ArrayList<Transaction> arrayList = new ArrayList<Transaction>(list);
    }

    @GET
    @Path("/test")
    public String test() {
        return "NICE TRY";
    }
}