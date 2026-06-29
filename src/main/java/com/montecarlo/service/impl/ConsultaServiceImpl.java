package com.montecarlo.service.impl;

import com.montecarlo.dto.ConsultaDTO;
import com.montecarlo.dto.ConsultaRegistroDTO;
import com.montecarlo.entity.Consulta;
import com.montecarlo.entity.Usuario;
import com.montecarlo.repository.ConsultaRepository;
import com.montecarlo.repository.UsuarioRepository;
import com.montecarlo.service.ConsultaService;
import com.montecarlo.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
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

        Usuario usuario = SecurityUtils.obtenerUsuarioAutenticado(usuarioRepository);

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

    @Override
    public List<ConsultaDTO> listarMisConsultas() {

        Usuario usuario = SecurityUtils.obtenerUsuarioAutenticado(usuarioRepository);

        return consultaRepository.listarMisConsultas(usuario.getCorreo())
                .stream()
                .map(this::mapToDTO)
                .toList();

    }

}