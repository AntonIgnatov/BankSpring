package net.bigmir.venzor.enteties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="curensy")
@NoArgsConstructor
@Getter
@Setter

public class Curensy {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Column(unique = true)
    private String name;
    private double rate;

    public Curensy(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

}
