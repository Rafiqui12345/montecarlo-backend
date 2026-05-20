package com.montecarlo.dto;

import com.montecarlo.entity.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private Long id;

    private String nombre;

    private String apellido;

    private String correo;

    private String telefono;

    private Role rol;
}