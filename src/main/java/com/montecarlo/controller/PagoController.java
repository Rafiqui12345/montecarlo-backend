package com.montecarlo.controller;

import com.montecarlo.dto.PagoDTO;
import com.montecarlo.dto.PagoRegistroDTO;
import com.montecarlo.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PagoDTO procesarPago(@RequestBody PagoRegistroDTO pagoRegistroDTO) {

        return pagoService.procesarPago(pagoRegistroDTO);

    }

}