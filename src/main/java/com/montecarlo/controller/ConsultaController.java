package com.montecarlo.controller;

import com.montecarlo.dto.ConsultaDTO;
import com.montecarlo.dto.ConsultaRegistroDTO;
import com.montecarlo.dto.RespuestaConsultaDTO;
import com.montecarlo.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaDTO registrar(@RequestBody ConsultaRegistroDTO dto){

        return consultaService.registrarConsulta(dto);

    }

    @GetMapping
    public List<ConsultaDTO> listar(){

        return consultaService.listarConsultas();

    }

    @GetMapping("/{id}")
    public ConsultaDTO obtener(@PathVariable Long id){

        return consultaService.obtenerConsulta(id);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){

        consultaService.eliminarConsulta(id);

    }

    @GetMapping("/mis-consultas")
    public ResponseEntity<List<ConsultaDTO>> listarMisConsultas(){

        return ResponseEntity.ok(
                consultaService.listarMisConsultas()
        );

    }

    @PatchMapping("/{id}/responder")
    public ConsultaDTO responderConsulta(
            @PathVariable Long id,
            @RequestBody RespuestaConsultaDTO dto){

        return consultaService.responderConsulta(id, dto);

    }

}