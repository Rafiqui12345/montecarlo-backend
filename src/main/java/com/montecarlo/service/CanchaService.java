package com.montecarlo.service;

import com.montecarlo.dto.CanchaDTO;
import com.montecarlo.dto.CanchaRegistroDTO;

import java.util.List;

public interface CanchaService {

    CanchaDTO registrarCancha(CanchaRegistroDTO canchaRegistroDTO);

    List<CanchaDTO> listarCanchas();

    CanchaDTO obtenerCanchaPorId(Long id);

    void eliminarCancha(Long id);
}