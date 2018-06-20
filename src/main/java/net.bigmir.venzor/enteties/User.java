package net.bigmir.venzor.enteties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

    public User(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }
    public void addAccount(Account account){
        this.accounts.add(account);
    }

    public Account getAccountById(long id){
        for(Account temp : accounts){
            if(temp.getId() == id){
                return temp;
            }
        }
        return null;
    }

    public void fillAccounts(double uah, double usd, double eur){
        this.accounts.add(new Account(this, "UAH", uah));
        this.accounts.add(new Account(this, "USD", usd));
        this.accounts.add(new Account(this, "EUR", eur));
    }
}
