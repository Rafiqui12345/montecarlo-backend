package com.montecarlo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistroDTO {

    private String nombre;

    private String apellido;

    private String correo;

    private String password;

    private String telefono;
}