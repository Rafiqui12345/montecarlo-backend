package com.montecarlo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CanchaDTO {

    private Long id;

    private String nombre;

    private String descripcion;

    private Double precioHora;

    private Boolean estado;
}