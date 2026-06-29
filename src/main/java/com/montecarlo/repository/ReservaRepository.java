package com.montecarlo.repository;

import com.montecarlo.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("""
        SELECT COUNT(r) > 0
        FROM Reserva r
        WHERE r.cancha.id = :canchaId
        AND r.fecha = :fecha
        AND r.horaInicio < :horaFin
        AND r.horaFin > :horaInicio
        """)
    boolean existeReservaSolapada(
            @Param("canchaId") Long canchaId,
            @Param("fecha") LocalDate fecha,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFin") LocalTime horaFin
    );

    List<Reserva> findByFechaOrderByHoraInicio(LocalDate fecha);

    @Query("SELECT COUNT(r) FROM Reserva r")
    Long contarReservas();

    @Query("""
        SELECT COUNT(r)
        FROM Reserva r
        WHERE r.estado='PENDIENTE'
        """)
    Long contarPendientes();

    @Query("""
        SELECT COUNT(r)
        FROM Reserva r
        WHERE r.estado='CONFIRMADA'
        """)
    Long contarConfirmadas();

    @Query("""
        SELECT COUNT(r)
        FROM Reserva r
        WHERE r.estado='CANCELADA'
        """)
    Long contarCanceladas();
}