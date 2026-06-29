package com.montecarlo.service.impl;

import com.montecarlo.dto.HistorialReservaDTO;
import com.montecarlo.entity.HistorialReserva;
import com.montecarlo.entity.Reserva;
import com.montecarlo.repository.HistorialReservaRepository;
import com.montecarlo.repository.ReservaRepository;
import com.montecarlo.service.HistorialReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialReservaServiceImpl implements HistorialReservaService {

    private final HistorialReservaRepository historialReservaRepository;
    private final ReservaRepository reservaRepository;

    @Override
    public void registrarCambioEstado(Long reservaId, String estado) {

        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        HistorialReserva historial = HistorialReserva.builder()
                .estado(estado)
                .fechaCambio(LocalDateTime.now())
                .reserva(reserva)
                .build();

        historialReservaRepository.save(historial);

    }

    @Override
    public List<HistorialReservaDTO> obtenerHistorial(Long reservaId) {

        return historialReservaRepository
                .findByReservaIdOrderByFechaCambioAsc(reservaId)
                .stream()
                .map(this::mapToDTO)
                .toList();

    }

    private HistorialReservaDTO mapToDTO(HistorialReserva historial){

        return HistorialReservaDTO.builder()
                .id(historial.getId())
                .estado(historial.getEstado())
                .fechaCambio(historial.getFechaCambio())
                .reservaId(historial.getReserva().getId())
                .build();

    }

}