package com.montecarlo.service.impl;

import com.montecarlo.dto.RegistroDTO;
import com.montecarlo.dto.UsuarioDTO;
import com.montecarlo.entity.Role;
import com.montecarlo.entity.Usuario;
import com.montecarlo.repository.UsuarioRepository;
import com.montecarlo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDTO registrarUsuario(RegistroDTO registroDTO) {

        if (usuarioRepository.existsByCorreo(registroDTO.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Usuario usuario = Usuario.builder()
                .nombre(registroDTO.getNombre())
                .apellido(registroDTO.getApellido())
                .correo(registroDTO.getCorreo())
                .password(passwordEncoder.encode(registroDTO.getPassword()))
                .telefono(registroDTO.getTelefono())
                .rol(Role.CLIENTE)
                .build();

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return UsuarioDTO.builder()
                .id(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .apellido(usuarioGuardado.getApellido())
                .correo(usuarioGuardado.getCorreo())
                .telefono(usuarioGuardado.getTelefono())
                .rol(usuarioGuardado.getRol())
                .build();
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {

        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> UsuarioDTO.builder()
                        .id(usuario.getId())
                        .nombre(usuario.getNombre())
                        .apellido(usuario.getApellido())
                        .correo(usuario.getCorreo())
                        .telefono(usuario.getTelefono())
                        .rol(usuario.getRol())
                        .build())
                .toList();
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .correo(usuario.getCorreo())
                .telefono(usuario.getTelefono())
                .rol(usuario.getRol())
                .build();
    }

    @Override
    public void eliminarUsuario(Long id) {

        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO mapToDTO(Usuario usuario) {

        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .telefono(usuario.getTelefono())
                .correo(usuario.getCorreo())
                .rol(usuario.getRol())
                .build();
    }

    @Override
    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setRol(usuarioDTO.getRol());

        Usuario usuarioActualizado = usuarioRepository.save(usuario);

        return mapToDTO(usuarioActualizado);
    }
}