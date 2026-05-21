package com.montecarlo.controller;

import com.montecarlo.dto.ReservaDTO;
import com.montecarlo.dto.ReservaRegistroDTO;
import com.montecarlo.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaDTO registrarReserva(@RequestBody ReservaRegistroDTO reservaRegistroDTO) {

        return reservaService.registrarReserva(reservaRegistroDTO);
    }

    @GetMapping
    public List<ReservaDTO> listarReservas() {

        return reservaService.listarReservas();
    }

    @GetMapping("/{id}")
    public ReservaDTO obtenerReservaPorId(@PathVariable Long id) {

        return reservaService.obtenerReservaPorId(id);
    }

    @PutMapping("/{id}")
    public ReservaDTO actualizarReserva(
            @PathVariable Long id,
            @RequestBody ReservaRegistroDTO reservaRegistroDTO
    ) {

        return reservaService.actualizarReserva(id, reservaRegistroDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarReserva(@PathVariable Long id) {

        reservaService.eliminarReserva(id);
    }
}