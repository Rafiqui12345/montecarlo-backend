package com.montecarlo.repository;

import com.montecarlo.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    @Query("""
    SELECT COALESCE(SUM(p.monto),0)
    FROM Pago p
    WHERE p.estado='APROBADO'
    """)
    Double ingresosTotales();

    @Query("""
       SELECT p
       FROM Pago p
       WHERE p.reserva.usuario.correo = :correo
       ORDER BY p.fechaPago DESC
       """)
    List<Pago> listarMisPagos(@Param("correo") String correo);
}