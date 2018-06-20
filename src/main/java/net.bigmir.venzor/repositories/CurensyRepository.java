package net.bigmir.venzor.repositories;

import net.bigmir.venzor.enteties.Curensy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CurensyRepository extends JpaRepository<Curensy, Long> {
    @Query("SELECT c.rate FROM Curensy c WHERE c.name = :name")
    double findByname(@Param("name") String name);
}
