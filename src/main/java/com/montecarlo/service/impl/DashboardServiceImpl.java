package com.montecarlo.service.impl;

import com.montecarlo.dto.DashboardDTO;
import com.montecarlo.repository.*;
import com.montecarlo.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UsuarioRepository usuarioRepository;
    private final CanchaRepository canchaRepository;
    private final ReservaRepository reservaRepository;
    private final PagoRepository pagoRepository;
    private final ConsultaRepository consultaRepository;

    @Override
    public DashboardDTO obtenerDashboard() {

        return DashboardDTO.builder()
                .totalUsuarios(usuarioRepository.contarUsuarios())
                .totalCanchas(canchaRepository.contarCanchas())
                .totalReservas(reservaRepository.contarReservas())
                .reservasPendientes(reservaRepository.contarPendientes())
                .reservasConfirmadas(reservaRepository.contarConfirmadas())
                .reservasCanceladas(reservaRepository.contarCanceladas())
                .ingresosTotales(pagoRepository.ingresosTotales())
                .totalConsultas(consultaRepository.contarConsultas())
                .build();

    }

}