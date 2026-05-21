package com.montecarlo.service;

import com.montecarlo.dto.RegistroDTO;
import com.montecarlo.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO registrarUsuario(RegistroDTO registroDTO);

    List<UsuarioDTO> listarUsuarios();

    UsuarioDTO obtenerUsuarioPorId(Long id);

    void eliminarUsuario(Long id);

    UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO);
}