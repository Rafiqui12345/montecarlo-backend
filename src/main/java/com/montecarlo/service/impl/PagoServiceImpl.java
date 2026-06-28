package com.montecarlo.service.impl;

import com.montecarlo.dto.PagoDTO;
import com.montecarlo.dto.PagoRegistroDTO;
import com.montecarlo.dto.ReservaRegistroDTO;
import com.montecarlo.entity.ConfiguracionClub;
import com.montecarlo.entity.Pago;
import com.montecarlo.entity.Reserva;
import com.montecarlo.repository.ConfiguracionClubRepository;
import com.montecarlo.repository.PagoRepository;
import com.montecarlo.service.EmailService;
import com.montecarlo.service.PagoService;
import com.montecarlo.service.ReservaService;
import com.montecarlo.util.CodigoOperacionGenerator;
import com.montecarlo.util.PaymentSimulator;
import com.montecarlo.util.PrecioCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final ConfiguracionClubRepository configuracionClubRepository;
    private final ReservaService reservaService;
    private final EmailService emailService;
    @Override
    @Transactional
    public PagoDTO procesarPago(PagoRegistroDTO pagoRegistroDTO) {

        // Validar tarjeta
        PaymentSimulator.validarPago(
                pagoRegistroDTO.getNumeroTarjeta(),
                pagoRegistroDTO.getCvv(),
                pagoRegistroDTO.getFechaExpiracion()
        );

        // Obtener configuración
        ConfiguracionClub configuracion = configuracionClubRepository
                .findByActivoTrue()
                .orElseThrow(() -> new RuntimeException("No existe una configuración activa."));

        // Calcular monto
        var monto = PrecioCalculator.calcularMonto(
                pagoRegistroDTO.getHoraInicio(),
                pagoRegistroDTO.getHoraFin(),
                configuracion.getPrecioHora()
        );

        // Crear reserva
        ReservaRegistroDTO reservaDTO = ReservaRegistroDTO.builder()
                .usuarioId(pagoRegistroDTO.getUsuarioId())
                .canchaId(pagoRegistroDTO.getCanchaId())
                .fecha(pagoRegistroDTO.getFecha())
                .horaInicio(pagoRegistroDTO.getHoraInicio())
                .horaFin(pagoRegistroDTO.getHoraFin())
                .build();

        Reserva reserva = reservaService.registrarReservaEntity(reservaDTO);

        // Crear pago
        Pago pago = Pago.builder()
                .monto(monto)
                .fechaPago(LocalDateTime.now())
                .codigoOperacion(CodigoOperacionGenerator.generarCodigo())
                .estado("APROBADO")
                .reserva(reserva)
                .build();

        Pago pagoGuardado = pagoRepository.save(pago);
        emailService.enviarBoleta(pagoGuardado);
        return mapToDTO(pagoGuardado);
    }

    private PagoDTO mapToDTO(Pago pago) {

        return PagoDTO.builder()
                .id(pago.getId())
                .monto(pago.getMonto())
                .fechaPago(pago.getFechaPago())
                .codigoOperacion(pago.getCodigoOperacion())
                .estado(pago.getEstado())
                .reservaId(pago.getReserva().getId())
                .build();
    }

}