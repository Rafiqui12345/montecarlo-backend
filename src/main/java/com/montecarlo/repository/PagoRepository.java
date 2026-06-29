package com.montecarlo.repository;

import com.montecarlo.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    @Query("""
    SELECT COALESCE(SUM(p.monto),0)
    FROM Pago p
    WHERE p.estado='APROBADO'
    """)
    Double ingresosTotales();
}