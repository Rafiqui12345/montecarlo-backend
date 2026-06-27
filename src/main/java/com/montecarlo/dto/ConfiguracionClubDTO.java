package com.montecarlo.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfiguracionClubDTO {

    private Long id;

    private LocalTime horaApertura;

    private LocalTime horaCierre;

    private Integer intervaloReserva;

    private BigDecimal precioHora;

    private Boolean activo;

}