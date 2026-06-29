package com.montecarlo.service.impl;

import com.montecarlo.dto.DisponibilidadDTO;
import com.montecarlo.entity.Cancha;
import com.montecarlo.entity.ConfiguracionClub;
import com.montecarlo.entity.Reserva;
import com.montecarlo.repository.CanchaRepository;
import com.montecarlo.repository.ConfiguracionClubRepository;
import com.montecarlo.repository.ReservaRepository;
import com.montecarlo.service.DisponibilidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DisponibilidadServiceImpl implements DisponibilidadService {

    private final ConfiguracionClubRepository configuracionClubRepository;
    private final ReservaRepository reservaRepository;
    private final CanchaRepository canchaRepository;

    @Override
    public List<DisponibilidadDTO> obtenerDisponibilidad(LocalDate fecha, Long canchaId) {

        Cancha cancha = canchaRepository.findById(canchaId)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        ConfiguracionClub configuracion = configuracionClubRepository.findByActivoTrue()
                .orElseThrow(() -> new RuntimeException("No existe una configuración activa."));

        List<Reserva> reservas =
                reservaRepository.findByCanchaIdAndFechaOrderByHoraInicio(
                        canchaId,
                        fecha
                );

        List<DisponibilidadDTO> disponibilidad = new ArrayList<>();

        LocalTime horaActual = configuracion.getHoraApertura();

        BigDecimal precioBloque = BigDecimal.valueOf(cancha.getPrecioHora())
                .multiply(BigDecimal.valueOf(configuracion.getIntervaloReserva()))
                .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);

        while (horaActual.isBefore(configuracion.getHoraCierre())) {

            LocalTime siguienteHora = horaActual.plusMinutes(configuracion.getIntervaloReserva());
            final LocalTime inicioBloque = horaActual;
            final LocalTime finBloque = siguienteHora;

            boolean ocupado = reservas.stream().anyMatch(reserva ->
                    inicioBloque.isBefore(reserva.getHoraFin()) &&
                            finBloque.isAfter(reserva.getHoraInicio())
            );

            disponibilidad.add(
                    DisponibilidadDTO.builder()
                            .horaInicio(inicioBloque)
                            .horaFin(finBloque)
                            .duracion(configuracion.getIntervaloReserva())
                            .disponible(!ocupado)
                            .precio(precioBloque)
                            .build()
            );

            horaActual = siguienteHora;

        }

        return disponibilidad;

    }

}