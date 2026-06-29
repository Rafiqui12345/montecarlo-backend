package com.montecarlo.controller;

import com.montecarlo.dto.HistorialReservaDTO;
import com.montecarlo.service.HistorialReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial")
@RequiredArgsConstructor
public class HistorialReservaController {

    private final HistorialReservaService historialReservaService;

    @GetMapping("/{reservaId}")
    public List<HistorialReservaDTO> obtenerHistorial(@PathVariable Long reservaId){

        return historialReservaService.obtenerHistorial(reservaId);

    }

}