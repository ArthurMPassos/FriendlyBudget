package friendly.budget.backend.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    final private String name;

    final private String password;

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
