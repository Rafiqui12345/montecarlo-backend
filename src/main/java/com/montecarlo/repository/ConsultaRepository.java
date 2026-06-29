package com.montecarlo.repository;

import com.montecarlo.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {

    @Query("SELECT COUNT(c) FROM Consulta c")
    Long contarConsultas();

}