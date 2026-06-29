package com.montecarlo.service.impl;

import com.montecarlo.dto.ConfiguracionClubDTO;
import com.montecarlo.entity.ConfiguracionClub;
import com.montecarlo.repository.ConfiguracionClubRepository;
import com.montecarlo.service.ConfiguracionClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfiguracionClubServiceImpl implements ConfiguracionClubService {

    private final ConfiguracionClubRepository configuracionClubRepository;

    @Override
    public ConfiguracionClubDTO obtenerConfiguracion() {

        ConfiguracionClub configuracion = configuracionClubRepository.findByActivoTrue()
                .orElseThrow(() -> new RuntimeException("No existe una configuración activa."));

        return mapToDTO(configuracion);
    }

    @Override
    public ConfiguracionClubDTO actualizarConfiguracion(ConfiguracionClubDTO dto) {

        ConfiguracionClub configuracion = configuracionClubRepository.findByActivoTrue()
                .orElse(
                        ConfiguracionClub.builder()
                                .activo(true)
                                .build()
                );

        configuracion.setHoraApertura(dto.getHoraApertura());
        configuracion.setHoraCierre(dto.getHoraCierre());
        configuracion.setIntervaloReserva(dto.getIntervaloReserva());

        ConfiguracionClub configuracionGuardada = configuracionClubRepository.save(configuracion);

        return mapToDTO(configuracionGuardada);
    }

    private ConfiguracionClubDTO mapToDTO(ConfiguracionClub configuracion) {

        return ConfiguracionClubDTO.builder()
                .id(configuracion.getId())
                .horaApertura(configuracion.getHoraApertura())
                .horaCierre(configuracion.getHoraCierre())
                .intervaloReserva(configuracion.getIntervaloReserva())
                .activo(configuracion.getActivo())
                .build();
    }

}