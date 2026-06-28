package com.montecarlo.service.impl;

import com.montecarlo.dto.ReservaDTO;
import com.montecarlo.dto.ReservaRegistroDTO;
import com.montecarlo.entity.Cancha;
import com.montecarlo.entity.Reserva;
import com.montecarlo.entity.Usuario;
import com.montecarlo.repository.CanchaRepository;
import com.montecarlo.repository.ReservaRepository;
import com.montecarlo.repository.UsuarioRepository;
import com.montecarlo.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CanchaRepository canchaRepository;

    @Override
    public ReservaDTO registrarReserva(ReservaRegistroDTO reservaRegistroDTO) {

        Reserva reserva = registrarReservaEntity(reservaRegistroDTO);

        return mapToDTO(reserva);
    }

    @Override
    public Reserva registrarReservaEntity(ReservaRegistroDTO reservaRegistroDTO) {

        boolean reservaExistente = reservaRepository.existeReservaSolapada(
                reservaRegistroDTO.getCanchaId(),
                reservaRegistroDTO.getFecha(),
                reservaRegistroDTO.getHoraInicio(),
                reservaRegistroDTO.getHoraFin()
        );

        if (reservaExistente) {
            throw new RuntimeException("Ya existe una reserva en ese horario");
        }

        if (!reservaRegistroDTO.getHoraInicio().isBefore(reservaRegistroDTO.getHoraFin())) {
            throw new RuntimeException("La hora de inicio debe ser menor que la hora de fin.");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String correo = authentication.getName();

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cancha cancha = canchaRepository.findById(reservaRegistroDTO.getCanchaId())
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        Reserva reserva = Reserva.builder()
                .fecha(reservaRegistroDTO.getFecha())
                .horaInicio(reservaRegistroDTO.getHoraInicio())
                .horaFin(reservaRegistroDTO.getHoraFin())
                .estado("PENDIENTE")
                .usuario(usuario)
                .cancha(cancha)
                .build();

        return reservaRepository.save(reserva);
    }

    @Override
    public List<ReservaDTO> listarReservas() {

        return reservaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public ReservaDTO obtenerReservaPorId(Long id) {

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        return mapToDTO(reserva);
    }

    @Override
    public ReservaDTO actualizarReserva(Long id, ReservaRegistroDTO reservaRegistroDTO) {

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String correo = authentication.getName();

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cancha cancha = canchaRepository.findById(reservaRegistroDTO.getCanchaId())
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        reserva.setFecha(reservaRegistroDTO.getFecha());
        reserva.setHoraInicio(reservaRegistroDTO.getHoraInicio());
        reserva.setHoraFin(reservaRegistroDTO.getHoraFin());
        reserva.setUsuario(usuario);
        reserva.setCancha(cancha);

        Reserva reservaActualizada = reservaRepository.save(reserva);

        return mapToDTO(reservaActualizada);
    }

    @Override
    public void eliminarReserva(Long id) {

        reservaRepository.deleteById(id);
    }

    private ReservaDTO mapToDTO(Reserva reserva) {

        return ReservaDTO.builder()
                .id(reserva.getId())
                .fecha(reserva.getFecha())
                .horaInicio(reserva.getHoraInicio())
                .horaFin(reserva.getHoraFin())
                .estado(reserva.getEstado())
                .usuarioId(reserva.getUsuario().getId())
                .canchaId(reserva.getCancha().getId())
                .build();
    }
}