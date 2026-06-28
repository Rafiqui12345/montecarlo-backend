package com.montecarlo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalTime;

public class PrecioCalculator {

    private PrecioCalculator() {
    }

    public static BigDecimal calcularMonto(
            LocalTime horaInicio,
            LocalTime horaFin,
            BigDecimal precioHora
    ) {

        long minutos = Duration.between(horaInicio, horaFin).toMinutes();

        return precioHora
                .multiply(BigDecimal.valueOf(minutos))
                .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);

    }

}