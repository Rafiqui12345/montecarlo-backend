package com.montecarlo.service;

import com.montecarlo.dto.ReservaDTO;
import com.montecarlo.dto.ReservaRegistroDTO;
import com.montecarlo.entity.Reserva;

import java.util.List;

public interface ReservaService {

    ReservaDTO registrarReserva(ReservaRegistroDTO reservaRegistroDTO);

    Reserva registrarReservaEntity(ReservaRegistroDTO reservaRegistroDTO);

    List<ReservaDTO> listarReservas();

    ReservaDTO obtenerReservaPorId(Long id);

    ReservaDTO actualizarReserva(Long id, ReservaRegistroDTO reservaRegistroDTO);

    ReservaDTO actualizarEstado(Long id, String estado);

    void eliminarReserva(Long id);
}