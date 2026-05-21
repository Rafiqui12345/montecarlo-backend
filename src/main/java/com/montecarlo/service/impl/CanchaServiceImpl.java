package com.montecarlo.service.impl;

import com.montecarlo.dto.CanchaDTO;
import com.montecarlo.dto.CanchaRegistroDTO;
import com.montecarlo.entity.Cancha;
import com.montecarlo.repository.CanchaRepository;
import com.montecarlo.service.CanchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CanchaServiceImpl implements CanchaService {

    private final CanchaRepository canchaRepository;

    @Override
    public CanchaDTO registrarCancha(CanchaRegistroDTO canchaRegistroDTO) {

        Cancha cancha = Cancha.builder()
                .nombre(canchaRegistroDTO.getNombre())
                .descripcion(canchaRegistroDTO.getDescripcion())
                .precioHora(canchaRegistroDTO.getPrecioHora())
                .estado(canchaRegistroDTO.getEstado())
                .build();

        Cancha canchaGuardada = canchaRepository.save(cancha);

        return mapToDTO(canchaGuardada);
    }

    @Override
    public List<CanchaDTO> listarCanchas() {

        return canchaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public CanchaDTO obtenerCanchaPorId(Long id) {

        Cancha cancha = canchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        return mapToDTO(cancha);
    }

    @Override
    public void eliminarCancha(Long id) {

        canchaRepository.deleteById(id);
    }

    private CanchaDTO mapToDTO(Cancha cancha) {

        return CanchaDTO.builder()
                .id(cancha.getId())
                .nombre(cancha.getNombre())
                .descripcion(cancha.getDescripcion())
                .precioHora(cancha.getPrecioHora())
                .estado(cancha.getEstado())
                .build();
    }

    @Override
    public CanchaDTO actualizarCancha(Long id, CanchaRegistroDTO canchaRegistroDTO) {

        Cancha cancha = canchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        cancha.setNombre(canchaRegistroDTO.getNombre());
        cancha.setDescripcion(canchaRegistroDTO.getDescripcion());
        cancha.setPrecioHora(canchaRegistroDTO.getPrecioHora());
        cancha.setEstado(canchaRegistroDTO.getEstado());

        Cancha canchaActualizada = canchaRepository.save(cancha);

        return mapToDTO(canchaActualizada);
    }
}