package com.montecarlo.service;

import com.montecarlo.entity.Consulta;
import com.montecarlo.entity.Pago;

public interface EmailService {

    void enviarBoleta(Pago pago);

    void enviarRespuestaConsulta(Consulta consulta);
}