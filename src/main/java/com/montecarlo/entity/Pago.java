package com.montecarlo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pago")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(nullable = false)
    private LocalDateTime fechaPago;

    @Column(nullable = false)
    private String codigoOperacion;

    @Column(nullable = false)
    private String estado;

    @OneToOne
    @JoinColumn(name = "reserva_id", nullable = false, unique = true)
    private Reserva reserva;

}