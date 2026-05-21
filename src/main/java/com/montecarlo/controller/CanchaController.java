package com.montecarlo.controller;

import com.montecarlo.dto.CanchaDTO;
import com.montecarlo.dto.CanchaRegistroDTO;
import com.montecarlo.service.CanchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/canchas")
@RequiredArgsConstructor
public class CanchaController {

    private final CanchaService canchaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CanchaDTO registrarCancha(@RequestBody CanchaRegistroDTO canchaRegistroDTO) {

        return canchaService.registrarCancha(canchaRegistroDTO);
    }

    @GetMapping
    public List<CanchaDTO> listarCanchas() {

        return canchaService.listarCanchas();
    }

    @GetMapping("/{id}")
    public CanchaDTO obtenerCanchaPorId(@PathVariable Long id) {

        return canchaService.obtenerCanchaPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCancha(@PathVariable Long id) {

        canchaService.eliminarCancha(id);
    }

    @PutMapping("/{id}")
    public CanchaDTO actualizarCancha(
            @PathVariable Long id,
            @RequestBody CanchaRegistroDTO canchaRegistroDTO
    ) {

        return canchaService.actualizarCancha(id, canchaRegistroDTO);
    }
}