package net.bigmir.venzor.repositories;

import net.bigmir.venzor.enteties.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
