package com.montecarlo.repository;

import com.montecarlo.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {

    @Query("SELECT COUNT(c) FROM Consulta c")
    Long contarConsultas();

    @Query("""
       SELECT c
       FROM Consulta c
       WHERE c.usuario.correo = :correo
       ORDER BY c.fecha DESC
       """)
    List<Consulta> listarMisConsultas(@Param("correo") String correo);

}