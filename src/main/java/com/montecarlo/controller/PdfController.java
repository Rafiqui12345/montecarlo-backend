package com.montecarlo.controller;

import com.montecarlo.entity.Pago;
import com.montecarlo.repository.PagoRepository;
import com.montecarlo.util.PdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pdf")
@RequiredArgsConstructor
public class PdfController {

    private final PagoRepository pagoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> descargarPdf(@PathVariable Long id) {

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        byte[] pdf = PdfGenerator.generarBoleta(pago);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=boleta.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);

    }

}