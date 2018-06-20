package net.bigmir.venzor.enteties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="transactions")
@NoArgsConstructor
@Getter
@Setter
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String fromUser;
    private String toUser;
    private String fromCurensy;
    private String toCurensy;
    private double amount;

    public Transactions(String fromUser, String toUser, String fromCurensy, String toCurensy, double amount) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.fromCurensy = fromCurensy;
        this.toCurensy = toCurensy;
        this.amount = amount;
    }
}
