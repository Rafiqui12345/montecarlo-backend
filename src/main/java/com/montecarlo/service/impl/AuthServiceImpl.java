package com.montecarlo.service.impl;

import com.montecarlo.dto.AuthResponseDTO;
import com.montecarlo.dto.LoginDTO;
import com.montecarlo.entity.Usuario;
import com.montecarlo.repository.UsuarioRepository;
import com.montecarlo.service.AuthService;
import com.montecarlo.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {

        Usuario usuario = usuarioRepository.findByCorreo(loginDTO.getCorreo())
                .orElseThrow(() ->
                        new RuntimeException("Credenciales inválidas"));

        boolean passwordCorrecta = passwordEncoder.matches(
                loginDTO.getPassword(),
                usuario.getPassword()
        );

        if (!passwordCorrecta) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(usuario.getCorreo());

        return AuthResponseDTO.builder()
                .token(token)
                .build();
    }
}