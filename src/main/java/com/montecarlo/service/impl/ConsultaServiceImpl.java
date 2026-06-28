package com.montecarlo.service.impl;

import com.montecarlo.dto.ConsultaDTO;
import com.montecarlo.dto.ConsultaRegistroDTO;
import com.montecarlo.entity.Consulta;
import com.montecarlo.entity.Usuario;
import com.montecarlo.repository.ConsultaRepository;
import com.montecarlo.repository.UsuarioRepository;
import com.montecarlo.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public ConsultaDTO registrarConsulta(ConsultaRegistroDTO dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String correo = authentication.getName();

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Consulta consulta = Consulta.builder()
                .asunto(dto.getAsunto())
                .mensaje(dto.getMensaje())
                .fecha(LocalDateTime.now())
                .usuario(usuario)
                .build();

        return mapToDTO(consultaRepository.save(consulta));
    }

    @Override
    public List<ConsultaDTO> listarConsultas() {

        return consultaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public ConsultaDTO obtenerConsulta(Long id) {

        return mapToDTO(
                consultaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Consulta no encontrada"))
        );
    }

    @Override
    public void eliminarConsulta(Long id) {

        consultaRepository.deleteById(id);

    }

    private ConsultaDTO mapToDTO(Consulta consulta){

        return ConsultaDTO.builder()
                .id(consulta.getId())
                .asunto(consulta.getAsunto())
                .mensaje(consulta.getMensaje())
                .fecha(consulta.getFecha())
                .usuarioId(consulta.getUsuario().getId())
                .build();

    }

}