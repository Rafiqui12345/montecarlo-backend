package com.montecarlo.service;

import com.montecarlo.dto.ConsultaDTO;
import com.montecarlo.dto.ConsultaRegistroDTO;
import com.montecarlo.dto.RespuestaConsultaDTO;

import java.util.List;

public interface ConsultaService {

    ConsultaDTO registrarConsulta(ConsultaRegistroDTO dto);

    List<ConsultaDTO> listarConsultas();

    ConsultaDTO obtenerConsulta(Long id);

    void eliminarConsulta(Long id);

    List<ConsultaDTO> listarMisConsultas();

    ConsultaDTO responderConsulta(
            Long id,
            RespuestaConsultaDTO dto
    );
}