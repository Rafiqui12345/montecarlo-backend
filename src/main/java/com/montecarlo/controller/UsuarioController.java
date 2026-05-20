package com.montecarlo.controller;

import com.montecarlo.dto.RegistroDTO;
import com.montecarlo.dto.UsuarioDTO;
import com.montecarlo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO registrarUsuario(@RequestBody RegistroDTO registroDTO) {

        return usuarioService.registrarUsuario(registroDTO);
    }

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {

        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtenerUsuarioPorId(@PathVariable Long id) {

        return usuarioService.obtenerUsuarioPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable Long id) {

        usuarioService.eliminarUsuario(id);
    }
}