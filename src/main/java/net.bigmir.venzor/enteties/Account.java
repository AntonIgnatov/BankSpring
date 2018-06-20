package net.bigmir.venzor.enteties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="accounts")
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    private String curensy;

    private double amount;

    public Account(User user, String curensy, double amount) {
        this.user = user;
        this.curensy = curensy;
        this.amount = amount;

    }
}
