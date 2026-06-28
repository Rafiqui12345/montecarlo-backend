package com.montecarlo.service.impl;

import com.montecarlo.entity.Pago;
import com.montecarlo.service.EmailService;
import com.montecarlo.util.PdfGenerator;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void enviarBoleta(Pago pago) {

        try {

            MimeMessage mensaje = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);

            helper.setTo(pago.getReserva().getUsuario().getCorreo());

            helper.setSubject("Boleta de pago - Club Montecarlo");

            helper.setText("""
                    Hola %s,

                    Gracias por tu reserva.

                    Adjuntamos tu boleta de pago en PDF.

                    Club Montecarlo
                    """.formatted(
                    pago.getReserva().getUsuario().getNombre()
            ));

            helper.addAttachment(
                    "Boleta.pdf",
                    new ByteArrayResource(PdfGenerator.generarBoleta(pago))
            );

            mailSender.send(mensaje);

        } catch (Exception e) {

            throw new RuntimeException("No se pudo enviar el correo.");

        }

    }

}