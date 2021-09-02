package friendly.budget.backend.models;

import javax.naming.Name;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.awt.*;

public class TransactionDTO {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private float value;
    private String date;
    private String description;

    public TransactionDTO(float value, String date, String description){
        this.value = value;
        this.date = date;
        this.description = description;
    }

    public TransactionDTO(Transaction transaction){
        this.value = transaction.getValue();
        this.date = transaction.getDate();
        this.description = transaction.getDescription();
    }

    public float getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setValue(float value){
        this.value = value;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
