package friendly.budget.backend.models;

import org.springframework.stereotype.Service;

@Service
public class User {
    private String name;

    public User (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
