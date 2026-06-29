package com.montecarlo.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponseDTO {

    private LocalDateTime fecha;

    private Integer status;

    private String mensaje;

}