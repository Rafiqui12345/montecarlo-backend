package com.montecarlo.repository;

import com.montecarlo.entity.HistorialReserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialReservaRepository extends JpaRepository<HistorialReserva, Long> {

    List<HistorialReserva> findByReservaIdOrderByFechaCambioAsc(Long reservaId);

}