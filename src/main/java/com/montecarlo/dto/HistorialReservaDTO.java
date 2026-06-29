package com.montecarlo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialReservaDTO {

    private Long id;

    private String estado;

    private LocalDateTime fechaCambio;

    private Long reservaId;

}