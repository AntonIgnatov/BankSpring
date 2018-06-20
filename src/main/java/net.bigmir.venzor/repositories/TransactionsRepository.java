package net.bigmir.venzor.repositories;

import net.bigmir.venzor.enteties.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    @Query("SELECT c FROM Transactions c WHERE c.fromUser = :name OR c.toUser = :name")
    List<Transactions> allByUser(@Param("name") String name);

    @Query("SELECT c FROM Transactions c WHERE c.fromCurensy = :name OR c.toCurensy = :name")
    List<Transactions> allByCurensy(@Param("name") String name);
}
