package friendly.budget.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Transaction {

    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    final private User user;
    final private float value;
    final private String date;
    final private String description;

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
