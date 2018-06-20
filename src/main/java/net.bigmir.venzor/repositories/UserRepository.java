package net.bigmir.venzor.repositories;

import net.bigmir.venzor.enteties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT c FROM User WHERE c.name = :name ")
//    User getByName(@Param("name") String name);
}
