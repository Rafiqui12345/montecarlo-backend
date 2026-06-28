package com.montecarlo.util;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class PaymentSimulator {

    private PaymentSimulator() {
    }

    public static void validarPago(
            String numeroTarjeta,
            String cvv,
            String fechaExpiracion
    ) {

        if (numeroTarjeta == null || numeroTarjeta.isBlank()) {
            throw new RuntimeException("El número de tarjeta es obligatorio.");
        }

        if (!numeroTarjeta.matches("\\d{16}")) {
            throw new RuntimeException("Número de tarjeta inválido.");
        }

        if (cvv == null || !cvv.matches("\\d{3}")) {
            throw new RuntimeException("CVV inválido.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

        YearMonth expiracion = YearMonth.parse(fechaExpiracion, formatter);

        if (expiracion.isBefore(YearMonth.now())) {
            throw new RuntimeException("La tarjeta ha expirado.");
        }

    }

}