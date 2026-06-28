package com.montecarlo.service;

import com.montecarlo.dto.PagoDTO;
import com.montecarlo.dto.PagoRegistroDTO;
import org.springframework.transaction.annotation.Transactional;

public interface PagoService {

    @Transactional
    PagoDTO procesarPago(PagoRegistroDTO pagoRegistroDTO);

}