package com.montecarlo.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisponibilidadDTO {

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private Integer duracion;

    private Boolean disponible;

    private BigDecimal precio;

}