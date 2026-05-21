package com.montecarlo.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaRegistroDTO {

    private LocalDate fecha;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private Long usuarioId;

    private Long canchaId;
}