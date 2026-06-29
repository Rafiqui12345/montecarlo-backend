package com.montecarlo.controller;

import com.montecarlo.dto.DisponibilidadDTO;
import com.montecarlo.service.DisponibilidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/disponibilidad")
@RequiredArgsConstructor
public class DisponibilidadController {

    private final DisponibilidadService disponibilidadService;

    @GetMapping
    public List<DisponibilidadDTO> obtenerDisponibilidad(

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fecha,

            @RequestParam
            Long canchaId

    ) {

        return disponibilidadService.obtenerDisponibilidad(fecha, canchaId);

    }

}