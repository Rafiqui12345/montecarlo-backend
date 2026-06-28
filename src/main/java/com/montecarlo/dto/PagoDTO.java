package com.montecarlo.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagoDTO {

    private Long id;

    private BigDecimal monto;

    private LocalDateTime fechaPago;

    private String codigoOperacion;

    private String estado;

    private Long reservaId;

}