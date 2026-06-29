package com.montecarlo.repository;

import com.montecarlo.entity.Cancha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CanchaRepository extends JpaRepository<Cancha, Long> {

    @Query("SELECT COUNT(c) FROM Cancha c")
    Long contarCanchas();

}