package com.montecarlo.service;

import com.montecarlo.dto.ConfiguracionClubDTO;

public interface ConfiguracionClubService {

    ConfiguracionClubDTO obtenerConfiguracion();

    ConfiguracionClubDTO actualizarConfiguracion(ConfiguracionClubDTO configuracionClubDTO);

}