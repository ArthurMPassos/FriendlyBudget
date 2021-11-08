package friendly.budget.backend.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Transaction {

    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    final private String username;
    final private float value;
    final private String date;
    final private String description;

    public Transaction (String username, float value, String date, String description){
        this.username = username;
        this.value = value;
        this.date = date;
        this.description = description;
    }

    //getters and setters
    public String getUsername() {
        return username;
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
