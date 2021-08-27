package friendly.budget.backend.models;

import org.springframework.stereotype.Service;

@Service
public class Transaction {

    private User user;
    private float value;
    private String date;
    private String description;

    public Transaction (User user, float value, String date, String description){
        this.user = user;
        this.value = value;
        this.date = date;
        this.description = description;
    }

    //getters and setters
    public User getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    public float getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

}
