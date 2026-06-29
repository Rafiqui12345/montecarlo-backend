package com.montecarlo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDTO {

    private Long totalUsuarios;

    private Long totalCanchas;

    private Long totalReservas;

    private Long reservasPendientes;

    private Long reservasConfirmadas;

    private Long reservasCanceladas;

    private Double ingresosTotales;

    private Long totalConsultas;

}