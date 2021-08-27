package friendly.budget.backend.resource;

import friendly.budget.backend.DAO.UserDAO;
import friendly.budget.backend.models.Transaction;
import friendly.budget.backend.models.User;
import friendly.budget.backend.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import java.util.List;

@Path("")
public class Requests {

    @Autowired
    private User user;

    @POST
    @Path("/login")
    public boolean login(@RequestBody final String name) {
        User notAutenticated = UserDAO.login(name);
        if (notAutenticated != null){
            this.user = notAutenticated;
            return true;
        }
        return false;
    }

    @PUT
    @Path("/add")
    public String add(@RequestBody final String json) {

        JsonUtil.fromJson(json, List.class);
        return null;
    }

}