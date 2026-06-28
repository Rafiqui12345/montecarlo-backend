package com.montecarlo.service;

import com.montecarlo.entity.Pago;

public interface EmailService {

    void enviarBoleta(Pago pago);

}