package com.montecarlo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultaDTO {

    private Long id;

    private String asunto;

    private String mensaje;

    private LocalDateTime fecha;

    private Long usuarioId;

}