package com.montecarlo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CodigoOperacionGenerator {

    private CodigoOperacionGenerator() {
    }

    public static String generarCodigo() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        return "MC-" + LocalDateTime.now().format(formatter);

    }

}