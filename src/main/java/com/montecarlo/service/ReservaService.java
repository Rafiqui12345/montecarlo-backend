package com.montecarlo.service;

import com.montecarlo.dto.ReservaDTO;
import com.montecarlo.dto.ReservaRegistroDTO;

import java.util.List;

public interface ReservaService {

    ReservaDTO registrarReserva(ReservaRegistroDTO reservaRegistroDTO);

    List<ReservaDTO> listarReservas();

    ReservaDTO obtenerReservaPorId(Long id);

    ReservaDTO actualizarReserva(Long id, ReservaRegistroDTO reservaRegistroDTO);

    void eliminarReserva(Long id);
}