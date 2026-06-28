package com.montecarlo.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagoRegistroDTO {

    private Long canchaId;

    private LocalDate fecha;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private String numeroTarjeta;

    private String nombreTitular;

    private String cvv;

    private String fechaExpiracion;

}