package com.montecarlo.service;

import com.montecarlo.dto.DisponibilidadDTO;

import java.time.LocalDate;
import java.util.List;

public interface DisponibilidadService {

    List<DisponibilidadDTO> obtenerDisponibilidad(LocalDate fecha, Long canchaId);

}