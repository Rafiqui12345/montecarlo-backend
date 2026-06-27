package com.montecarlo.controller;

import com.montecarlo.dto.ConfiguracionClubDTO;
import com.montecarlo.service.ConfiguracionClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuracion")
@RequiredArgsConstructor
public class ConfiguracionClubController {

    private final ConfiguracionClubService configuracionClubService;

    @GetMapping
    public ConfiguracionClubDTO obtenerConfiguracion() {

        return configuracionClubService.obtenerConfiguracion();
    }

    @PutMapping
    public ConfiguracionClubDTO actualizarConfiguracion(
            @RequestBody ConfiguracionClubDTO configuracionClubDTO) {

        return configuracionClubService.actualizarConfiguracion(configuracionClubDTO);
    }

}