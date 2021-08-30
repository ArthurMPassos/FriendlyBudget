package friendly.budget.backend.models;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    final private String name;

    public User (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
