package com.montecarlo.util;

import com.montecarlo.entity.Usuario;
import com.montecarlo.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static Usuario obtenerUsuarioAutenticado(UsuarioRepository usuarioRepository) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String correo = authentication.getName();

        return usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    }

}