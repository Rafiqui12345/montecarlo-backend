package com.montecarlo.service.impl;

import com.montecarlo.entity.Consulta;
import com.montecarlo.entity.Pago;
import com.montecarlo.service.EmailService;
import com.montecarlo.util.PdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


import java.util.Base64;
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final RestTemplate restTemplate;
    private static final String BREVO_URL = "https://api.brevo.com/v3/smtp/email";

    @Value("${BREVO_API_KEY}")
    private String apiKey;

    @Override
    public void enviarBoleta(Pago pago) {

        try {

            String pdfBase64 = Base64.getEncoder()
                    .encodeToString(
                            PdfGenerator.generarBoleta(pago)
                    );

            String body = """
                {
                  "sender": {
                    "name": "Club Montecarlo",
                    "email": "lunadepluton911@gmail.com"
                  },
                  "to": [
                    {
                      "email": "%s"
                    }
                  ],
                  "subject": "Boleta de pago - Club Montecarlo",
                  "textContent": "Hola %s, gracias por tu reserva. Adjuntamos tu boleta de pago.",
                  "attachment": [
                    {
                      "content": "%s",
                      "name": "Boleta.pdf"
                    }
                  ]
                }
                """.formatted(
                    pago.getReserva()
                            .getUsuario()
                            .getCorreo(),

                    pago.getReserva()
                            .getUsuario()
                            .getNombre(),

                    pdfBase64
            );

            enviarCorreo(body);

        } catch (Exception e) {

            throw new RuntimeException(
                    "No se pudo enviar el correo.",
                    e
            );

        }

    }

    @Override
    public void enviarRespuestaConsulta(Consulta consulta) {

        try {

            String body = """
            {
              "sender": {
                "name": "Club Montecarlo",
                "email": "lunadepluton911@gmail.com"
              },
              "to": [
                {
                  "email": "%s"
                }
              ],
              "subject": "Respuesta a su consulta - Club Montecarlo",
              "textContent": "Hola %s,\\n\\nHemos respondido la consulta que realizó.\\n\\n--------------------------------------------\\n\\nASUNTO:\\n\\n%s\\n\\n--------------------------------------------\\n\\nSU CONSULTA:\\n\\n%s\\n\\n--------------------------------------------\\n\\nRESPUESTA:\\n\\n%s\\n\\n--------------------------------------------\\n\\nGracias por comunicarse con nosotros.\\n\\nClub Montecarlo"
            }
            """.formatted(
                    consulta.getUsuario().getCorreo(),
                    consulta.getUsuario().getNombre(),
                    consulta.getAsunto(),
                    consulta.getMensaje(),
                    consulta.getRespuesta()
            );

           enviarCorreo(body);

        } catch (Exception e) {

            throw new RuntimeException(
                    "No se pudo enviar la respuesta.",
                    e
            );

        }

    }

    private void enviarCorreo(String body) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);

        HttpEntity<String> request =
                new HttpEntity<>(body, headers);

        restTemplate.postForEntity(
                BREVO_URL,
                request,
                String.class
        );

    }

}