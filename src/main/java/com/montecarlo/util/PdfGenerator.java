package com.montecarlo.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.montecarlo.entity.Pago;
import com.montecarlo.entity.Reserva;

import java.io.ByteArrayOutputStream;

public class PdfGenerator {

    private PdfGenerator() {
    }

    public static byte[] generarBoleta(Pago pago) {

        try {

            Document document = new Document(PageSize.A4);
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, output);

            document.open();

            Font titulo = new Font(Font.HELVETICA, 20, Font.BOLD);

            Paragraph encabezado = new Paragraph("CLUB MONTECARLO", titulo);
            encabezado.setAlignment(Element.ALIGN_CENTER);

            document.add(encabezado);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("BOLETA DE PAGO"));
            document.add(new Paragraph(" "));

            Reserva reserva = pago.getReserva();

            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(100);

            agregarFila(tabla, "Código Operación", pago.getCodigoOperacion());
            agregarFila(tabla, "Estado", pago.getEstado());
            agregarFila(tabla, "Monto", "S/. " + pago.getMonto());
            agregarFila(tabla, "Fecha Pago", pago.getFechaPago().toString());

            agregarFila(tabla, "Cliente",
                    reserva.getUsuario().getNombre() + " " +
                            reserva.getUsuario().getApellido());

            agregarFila(tabla, "Cancha",
                    reserva.getCancha().getNombre());

            agregarFila(tabla, "Fecha Reserva",
                    reserva.getFecha().toString());

            agregarFila(tabla, "Horario",
                    reserva.getHoraInicio() + " - " + reserva.getHoraFin());

            document.add(tabla);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Gracias por confiar en Club Montecarlo."));
            document.add(new Paragraph("Este documento fue generado automáticamente."));

            document.close();

            return output.toByteArray();

        } catch (Exception e) {

            throw new RuntimeException("Error al generar el PDF.");

        }

    }

    private static void agregarFila(PdfPTable tabla, String campo, String valor) {

        PdfPCell c1 = new PdfPCell(new Phrase(campo));
        PdfPCell c2 = new PdfPCell(new Phrase(valor));

        tabla.addCell(c1);
        tabla.addCell(c2);

    }

}