package com.montecarlo.service;

import com.montecarlo.dto.HistorialReservaDTO;

import java.util.List;

public interface HistorialReservaService {

    void registrarCambioEstado(Long reservaId, String estado);

    List<HistorialReservaDTO> obtenerHistorial(Long reservaId);

}