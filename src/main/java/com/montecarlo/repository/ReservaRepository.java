package com.montecarlo.repository;

import com.montecarlo.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalTime;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    boolean existsByCanchaIdAndFechaAndHoraInicioLessThanAndHoraFinGreaterThan(
            Long canchaId,
            LocalDate fecha,
            LocalTime horaFin,
            LocalTime horaInicio
    );

}