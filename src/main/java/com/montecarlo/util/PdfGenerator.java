package com.montecarlo.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.montecarlo.entity.Pago;
import com.montecarlo.entity.Reserva;
import java.io.ByteArrayOutputStream;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PdfGenerator {

    private PdfGenerator() {
    }

    public static byte[] generarBoleta(Pago pago) {

        try {
            Document document = new Document(PageSize.A4);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, output);
            document.open();

            try {
                Image logo = Image.getInstance("https://scontent.flim33-1.fna.fbcdn.net/v/t39.30808-6/411985095_898453555009190_5520567224719629820_n.jpg?stp=dst-jpg_tt6&cstp=mx1160x1141&ctp=s1160x1141&_nc_cat=104&_nc_map=urlgen_bucketless&ccb=1-7&_nc_sid=6ee11a&_nc_ohc=vUaP48rRzMAQ7kNvwF-Rnz3&_nc_oc=Adr_3KkyoOWi6Oprv6tLujwTFbkKUs7-XIiV42luAVgdRmK7lboNjm_Es1Wqh1vvKxE&_nc_zt=23&_nc_ht=scontent.flim33-1.fna&_nc_gid=GPJH6wIi9zR95Qg1n8GlUQ&_nc_ss=7b289&oh=00_AQAq3O35sHG_C6wc7w-HYrn7v131qGBgxxYUJEv6_Vvnww&oe=6A562A58");
                logo.scaleToFit(90, 90);
                logo.setAlignment(Image.ALIGN_CENTER);

                document.add(logo);
            } catch (Exception e) {
                System.out.println("No se encontró el logo.");
            }

            Font titulo = new Font(Font.HELVETICA, 22, Font.BOLD, new Color(52, 73, 94));
            Font subtitulo = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font normal = new Font(Font.HELVETICA, 11);
            Font monto = new Font(Font.HELVETICA, 20, Font.BOLD, new Color(39, 174, 96));

            Paragraph encabezado = new Paragraph("CLUB MONTECARLO", titulo);
            encabezado.setAlignment(Element.ALIGN_CENTER);
            document.add(encabezado);

            Paragraph boleta = new Paragraph("BOLETA DE PAGO", subtitulo);
            boleta.setAlignment(Element.ALIGN_CENTER);
            document.add(boleta);

            document.add(new Paragraph(" "));

            Reserva reserva = pago.getReserva();

            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy - h:mm a");

            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{35f, 65f});

            agregarFila(tabla, "Código Operación", pago.getCodigoOperacion());
            agregarFila(tabla, "Estado", pago.getEstado());
            agregarFila(tabla, "Fecha Pago", pago.getFechaPago().format(formatoFechaHora));
            agregarFila(tabla, "Cliente", reserva.getUsuario().getNombre() + " " + reserva.getUsuario().getApellido());
            agregarFila(tabla, "Cancha", reserva.getCancha().getNombre());
            agregarFila(tabla, "Fecha Reserva", reserva.getFecha().format(formatoFecha));
            agregarFila(tabla, "Horario", formatearHora(reserva.getHoraInicio()) + " - " + formatearHora(reserva.getHoraFin()));

            document.add(tabla);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Paragraph totalTitulo = new Paragraph("TOTAL PAGADO", subtitulo);
            totalTitulo.setAlignment(Element.ALIGN_CENTER);
            document.add(totalTitulo);

            Paragraph total = new Paragraph("S/. " + String.format("%.2f", pago.getMonto()), monto);
            total.setAlignment(Element.ALIGN_CENTER);
            document.add(total);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Paragraph gracias = new Paragraph("Gracias por confiar en Club Montecarlo.", normal);
            gracias.setAlignment(Element.ALIGN_CENTER);
            document.add(gracias);

            Paragraph generado = new Paragraph("Este documento fue generado automáticamente.", normal);
            generado.setAlignment(Element.ALIGN_CENTER);
            document.add(generado);

            document.close();

            return output.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar la boleta PDF.", e);
        }
    }

    private static void agregarFila(PdfPTable tabla, String campo, String valor) {
        PdfPCell c1 = new PdfPCell(new Phrase(campo));
        PdfPCell c2 = new PdfPCell(new Phrase(valor));

        c1.setBackgroundColor(new Color(240, 240, 240));

        c1.setPadding(8);
        c2.setPadding(8);

        tabla.addCell(c1);
        tabla.addCell(c2);
    }

    private static String formatearHora(LocalTime hora) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("h:mm a");

        return hora.format(formato);
    }
}