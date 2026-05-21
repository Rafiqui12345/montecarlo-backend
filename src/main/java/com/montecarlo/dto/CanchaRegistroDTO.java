package com.montecarlo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CanchaRegistroDTO {

    private String nombre;

    private String descripcion;

    private Double precioHora;

    private Boolean estado;
}