package com.montecarlo.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaDTO {

    private Long id;

    private LocalDate fecha;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private String estado;

    private Long usuarioId;

    private Long canchaId;
}