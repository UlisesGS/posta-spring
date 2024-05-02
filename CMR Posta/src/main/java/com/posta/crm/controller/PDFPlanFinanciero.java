/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.posta.crm.entity.Client;
import com.posta.crm.repository.ClientRepository;
import com.posta.crm.repository.canvas.ProcessRepository;
import com.posta.crm.repository.empresario.ProcessEmpresarioRepository;
import com.posta.crm.entity.Process;
import com.posta.crm.entity.financiero.BusinessPlanFinancial;
import com.posta.crm.entity.financiero.partes.EstructuraMercado;
import com.posta.crm.service.financial.BusinessPlanFinancialServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author crowl
 */
@RestController
@RequestMapping("/pdf2")
@CrossOrigin(origins = "*")
public class PDFPlanFinanciero {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProcessEmpresarioRepository processEmpresarioRepository;
    @Autowired
    private ProcessRepository procesoRepos;
    @Autowired
    private BusinessPlanFinancialServiceImpl financialService;

    @GetMapping("/financieroVentas/{id}")
    public void generarDiagnostico(HttpServletResponse response, @PathVariable Long id) throws DocumentException, IOException {

        // Establecer el tipo de contenido de la respuesta como PDF
        response.setContentType("application/pdf");
        // Establecer el encabezado para indicar la descarga del archivo PDF
        response.setHeader("Content-Disposition", "attachment; filename=financieroVentas.pdf");

        try {

            // Crear una instancia del documento y del escritor
            Document document = new Document(PageSize.A4.rotate());

            OutputStream outputStream = response.getOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.setMargins(document.leftMargin(), document.rightMargin(), 50, 50);

            // Crear una instancia de la clase PdfHeaderEventHandler para manejar el encabezado
            PDFPlanFinanciero.PdfHeaderEventHandler1 headerHandler = new PDFPlanFinanciero.PdfHeaderEventHandler1();
            writer.setPageEvent(headerHandler);

            // Abrir el documento para escribir el contenido
            document.open();

            Client client = clientRepository.findById(id).get();
            Process proceso = procesoRepos.findOneByClientId(client.id);
            BusinessPlanFinancial planFinanciero = proceso.getBusinessPlanFinancial();

            // Agregar contenido al PDF
            // Crear un párrafo vacío antes del título para agregar espacio entre el título y el primer párrafo
            //Crear Fecha para portada
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
            String formattedDate = dateFormat.format(currentDate).toUpperCase();

            Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.BOLD);

            Paragraph title1 = new Paragraph("Presupuesto de Ventas", titleFont);

            title1.setAlignment(Element.ALIGN_CENTER);
            document.add(title1);

            // Aquí deberías obtener los datos de tu base de datos, si los necesitas
            // Establecer el estilo de fuente para el contenido
            Font contentFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD);
            //Font atributos = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.DARK_GRAY);
            Font contentFont1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
            Font contentFont2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
            Font contentFont3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
            Font contentFont4 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.NORMAL);
            Font contentFont5 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.NORMAL);

            Paragraph title1a = new Paragraph("Estructura de Mercado- Unidades", contentFont1);

            title1a.setAlignment(Element.ALIGN_CENTER);
            document.add(title1a);
            document.add(new Paragraph("\n"));
            //Crea tabla con 3 columnas
            PdfPTable table = new PdfPTable(2);
            table.setWidths(new float[]{70f, 30f});

            BaseColor colorHeader = new BaseColor(248, 140, 140);
            BaseColor colorHeader2 = new BaseColor(248, 199, 199);
            //Agrega el Titulo a la fila
            PdfPCell cell = new PdfPCell(new Paragraph("Nombre del Producto(Bien o Servicio)", contentFont4));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(colorHeader);
            table.addCell(cell);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Venta Unidades 1 Año", contentFont4));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBackgroundColor(colorHeader);
            table.addCell(cell1);

            List<EstructuraMercado> estructuraMercado = planFinanciero.getPresupuestoVenta().getEstructuraMercado();
            for (int i = 0; i < planFinanciero.getPresupuestoVenta().getEstructuraMercado().size(); i++) {
                EstructuraMercado estructura = estructuraMercado.get(i);

                PdfPCell cell1a = new PdfPCell(new Paragraph(estructura.getProducto(), contentFont5));
                table.addCell(cell1a);
                PdfPCell cell1b = new PdfPCell(new Paragraph(estructura.getCantidad().toString(), contentFont5));
                cell1b.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell1b);
            }
            PdfPCell cell1a = new PdfPCell(new Paragraph("Total", contentFont5));
            cell1a.setBackgroundColor(colorHeader);
            table.addCell(cell1a);
            PdfPCell cell1b = new PdfPCell(new Paragraph(planFinanciero.getPresupuestoVenta().getTotalProductos().toString(), contentFont5));
            cell1b.setBackgroundColor(colorHeader);
            cell1b.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell1b);

            document.add(table);
            document.add(new Paragraph("\n"));
            Paragraph title1b = new Paragraph("Capacidad Mano de Obra Instalada", contentFont1);
            title1b.setAlignment(Element.ALIGN_CENTER);
            document.add(title1b);
            document.add(new Paragraph("\n"));

            PdfPTable table1 = new PdfPTable(8);
            table1.setWidths(new float[]{12f, 12f, 12f, 12f, 15f, 10f, 16f, 10f});

            PdfPCell cel01 = new PdfPCell(new Paragraph("Nombre del Producto(Bien o Servicio)", contentFont5));

            cel01.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel01.setColspan(2);
            cel01.setBackgroundColor(colorHeader);
            table1.addCell(cel01);

            PdfPCell cel02 = new PdfPCell(new Paragraph("N° de Horas Año Operario Turno", contentFont5));
            cel02.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel02.setBackgroundColor(colorHeader);
            table1.addCell(cel02);

            PdfPCell cel03 = new PdfPCell(new Paragraph("Unidades Hora Hombre", contentFont5));
            cel03.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel03.setBackgroundColor(colorHeader);
            table1.addCell(cel03);

            PdfPCell cel04 = new PdfPCell(new Paragraph("Capacidad Instalada en U. por Operario", contentFont5));
            cel04.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel04.setBackgroundColor(colorHeader);
            table1.addCell(cel04);

            PdfPCell cel05 = new PdfPCell(new Paragraph("Tiempo de Dedicacion en %", contentFont5));
            cel05.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel05.setBackgroundColor(colorHeader);
            table1.addCell(cel05);

            PdfPCell cel06 = new PdfPCell(new Paragraph("Capacidad Instalada Unidades", contentFont5));
            cel06.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel06.setBackgroundColor(colorHeader);
            table1.addCell(cel06);

            PdfPCell cel07 = new PdfPCell(new Paragraph("Capacidad Requerida en Unidades", contentFont5));
            cel07.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel07.setBackgroundColor(colorHeader);
            table1.addCell(cel07);

            for (int i = 0; i < planFinanciero.getPresupuestoVenta().getEstructuraMercado().size(); i++) {
                EstructuraMercado estructura = estructuraMercado.get(i);

                PdfPCell cell01 = new PdfPCell(new Paragraph(estructura.getProducto(), contentFont5));
                table1.addCell(cell01);
                PdfPCell cell02 = new PdfPCell(new Paragraph(estructura.getTipo().toString(), contentFont5));
                cell02.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table1.addCell(cell02);
                PdfPCell cell03 = new PdfPCell(new Paragraph(estructura.getHorasOperario().toString(), contentFont5));
                cell03.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table1.addCell(cell03);
                PdfPCell cell04 = new PdfPCell(new Paragraph(estructura.getUnidadHoraHombre().toString(), contentFont5));
                cell04.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table1.addCell(cell04);
                PdfPCell cell05 = new PdfPCell(new Paragraph(estructura.getCapacidadInstaladaPorOperario().toString(), contentFont5));
                cell05.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table1.addCell(cell05);
                PdfPCell cell06 = new PdfPCell(new Paragraph(estructura.getTiempoDecicacion().toString(), contentFont5));
                cell06.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table1.addCell(cell06);
                PdfPCell cell07 = new PdfPCell(new Paragraph(estructura.getCapacidadInstaladaUnidades().toString(), contentFont5));
                cell07.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table1.addCell(cell07);
                PdfPCell cell08 = new PdfPCell(new Paragraph(estructura.getCantidad().toString(), contentFont5));
                cell08.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table1.addCell(cell08);
            }
            System.out.println("Manola");
            PdfPCell cell01a = new PdfPCell(new Paragraph("Total", contentFont5));
            cell01a.setBackgroundColor(colorHeader);
            cell01a.setColspan(4);
            table1.addCell(cell01a);
            PdfPCell cell01b = new PdfPCell(new Paragraph(planFinanciero.getPresupuestoVenta().getTotalCapacidadOperario().toString(), contentFont5));
            cell01b.setBackgroundColor(colorHeader);
            cell01b.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table1.addCell(cell01b);
            PdfPCell cell01e = new PdfPCell(new Paragraph("", contentFont5));
            cell01e.setBackgroundColor(colorHeader);
            cell01e.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table1.addCell(cell01e);
            PdfPCell cell01c = new PdfPCell(new Paragraph(planFinanciero.getPresupuestoVenta().getTotalCapacidadInstalada().toString(), contentFont5));
            cell01c.setBackgroundColor(colorHeader);
            cell01c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table1.addCell(cell01c);
            PdfPCell cell01d = new PdfPCell(new Paragraph(planFinanciero.getPresupuestoVenta().getTotalProductos().toString(), contentFont5));
            cell01d.setBackgroundColor(colorHeader);
            cell01d.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table1.addCell(cell01d);

            document.add(table1);

            document.add(new Paragraph("\n"));
            Paragraph title1c = new Paragraph("Ventas Anuales por Producto", contentFont1);
            title1c.setAlignment(Element.ALIGN_CENTER);
            document.add(title1c);
            document.add(new Paragraph("\n"));

            PdfPTable table2 = new PdfPTable(4);
            table2.setWidths(new float[]{25f, 25f, 25f, 25f});

            PdfPCell cellb01 = new PdfPCell(new Paragraph("Nombre del Producto(Bien o Servicio)", contentFont5));

            cellb01.setHorizontalAlignment(Element.ALIGN_CENTER);

            cellb01.setBackgroundColor(colorHeader);
            table2.addCell(cellb01);

            PdfPCell cellb02 = new PdfPCell(new Paragraph("Unidades a Vender", contentFont5));
            cellb02.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellb02.setBackgroundColor(colorHeader);
            table2.addCell(cellb02);

            PdfPCell cellb03 = new PdfPCell(new Paragraph("Precio de Venta Unitario", contentFont5));
            cellb03.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellb03.setBackgroundColor(colorHeader);
            table2.addCell(cellb03);

            PdfPCell cellb04 = new PdfPCell(new Paragraph("Ventas Totales $ Año 1", contentFont5));
            cellb04.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellb04.setBackgroundColor(colorHeader);
            table2.addCell(cellb04);

            for (int i = 0; i < planFinanciero.getPresupuestoVenta().getEstructuraMercado().size(); i++) {
                EstructuraMercado estructura = estructuraMercado.get(i);

                PdfPCell cell02a = new PdfPCell(new Paragraph(estructura.getProducto(), contentFont5));
                cell02a.setBackgroundColor(colorHeader);
                table2.addCell(cell02a);
                PdfPCell cell02b = new PdfPCell(new Paragraph(estructura.getCantidad().toString(), contentFont5));
                cell02b.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table2.addCell(cell02b);
                PdfPCell cell02e = new PdfPCell(new Paragraph("$ " + estructura.getPrecioUnitario().toString(), contentFont5));
                cell02e.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table2.addCell(cell02e);
                PdfPCell cell02c = new PdfPCell(new Paragraph("$ " + estructura.getPrecioTotal().toString(), contentFont5));
                cell02c.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table2.addCell(cell02c);

            }

            PdfPCell cell02a = new PdfPCell(new Paragraph("Total", contentFont5));
            cell02a.setBackgroundColor(colorHeader);
            table2.addCell(cell02a);
            PdfPCell cell02b = new PdfPCell(new Paragraph(planFinanciero.getPresupuestoVenta().getTotalProductos().toString(), contentFont5));
            cell02b.setBackgroundColor(colorHeader);
            cell02b.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table2.addCell(cell02b);
            PdfPCell cell02e = new PdfPCell(new Paragraph("$ " + planFinanciero.getPresupuestoVenta().getTotalPrecioUnitario().toString(), contentFont5));
            cell02e.setBackgroundColor(colorHeader);
            cell02e.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table2.addCell(cell02e);
            PdfPCell cell02c = new PdfPCell(new Paragraph("$ " + planFinanciero.getPresupuestoVenta().getTotalTotal().toString(), contentFont5));
            cell02c.setBackgroundColor(colorHeader);
            cell02c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table2.addCell(cell02c);

            document.add(table2);

//            Paragraph title1d = new Paragraph("Incremento en % del Precio                           Ciclicidad de Ventas", contentFont1);
//            title1d.setAlignment(Element.ALIGN_CENTER);
//            document.add(title1d);
//            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));

            PdfPTable table3 = new PdfPTable(3);
            float[] columnWidthsInPoints = {141.75f, 141.75f, 141.75f}; // Anchuras en puntos (5 cm = 141.75 puntos aproximadamente)
            table3.setWidths(columnWidthsInPoints);
            table3.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell cellc01 = new PdfPCell(new Paragraph("Periodo", contentFont5));
            cellc01.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellc01.setBackgroundColor(colorHeader);
            table3.addCell(cellc01);
            PdfPCell cellc02 = new PdfPCell(new Paragraph("IPC/ Inflacion %", contentFont5));
            cellc02.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellc02.setBackgroundColor(colorHeader);
            table3.addCell(cellc02);
            PdfPCell cellc03 = new PdfPCell(new Paragraph("Base + IPC", contentFont5));
            cellc03.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellc03.setBackgroundColor(colorHeader);
            table3.addCell(cellc03);

            Double ipc1 = planFinanciero.getIPC1() + 100;
            Double ipc2 = planFinanciero.getIPC2() + 100;
            Double ipc3 = planFinanciero.getIPC3() + 100;
            Double ipc4 = planFinanciero.getIPC4() + 100;

            PdfPCell celld01 = new PdfPCell(new Paragraph("AÑO 2", contentFont5));
            celld01.setHorizontalAlignment(Element.ALIGN_CENTER);
            celld01.setBackgroundColor(colorHeader2);
            table3.addCell(celld01);
            PdfPCell celld02 = new PdfPCell(new Paragraph(planFinanciero.getIPC1().toString(), contentFont5));
            celld02.setHorizontalAlignment(Element.ALIGN_CENTER);
            celld02.setBackgroundColor(colorHeader2);
            table3.addCell(celld02);
            PdfPCell celld03 = new PdfPCell(new Paragraph(ipc1.toString() + "%", contentFont5));
            celld03.setHorizontalAlignment(Element.ALIGN_CENTER);
            celld03.setBackgroundColor(colorHeader2);
            table3.addCell(celld03);

            PdfPCell celle01 = new PdfPCell(new Paragraph("AÑO 3", contentFont5));
            celle01.setHorizontalAlignment(Element.ALIGN_CENTER);
            celle01.setBackgroundColor(colorHeader2);
            table3.addCell(celle01);
            PdfPCell celle02 = new PdfPCell(new Paragraph(planFinanciero.getIPC2().toString(), contentFont5));
            celle02.setHorizontalAlignment(Element.ALIGN_CENTER);
            celle02.setBackgroundColor(colorHeader2);
            table3.addCell(celle02);
            PdfPCell celle03 = new PdfPCell(new Paragraph(ipc2.toString() + "%", contentFont5));
            celle03.setHorizontalAlignment(Element.ALIGN_CENTER);
            celle03.setBackgroundColor(colorHeader2);
            table3.addCell(celle03);

            PdfPCell cellf01 = new PdfPCell(new Paragraph("AÑO 4", contentFont5));
            cellf01.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellf01.setBackgroundColor(colorHeader2);
            table3.addCell(cellf01);
            PdfPCell cellf02 = new PdfPCell(new Paragraph(planFinanciero.getIPC3().toString(), contentFont5));
            cellf02.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellf02.setBackgroundColor(colorHeader2);
            table3.addCell(cellf02);
            PdfPCell cellf03 = new PdfPCell(new Paragraph(ipc3.toString() + "%", contentFont5));
            cellf03.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellf03.setBackgroundColor(colorHeader2);
            table3.addCell(cellf03);

            PdfPCell cellg01 = new PdfPCell(new Paragraph("AÑO 5", contentFont5));
            cellg01.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellg01.setBackgroundColor(colorHeader2);
            table3.addCell(cellg01);
            PdfPCell cellg02 = new PdfPCell(new Paragraph(planFinanciero.getIPC4().toString(), contentFont5));
            cellg02.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellg02.setBackgroundColor(colorHeader2);
            table3.addCell(cellg02);
            PdfPCell cellg03 = new PdfPCell(new Paragraph(ipc4.toString() + "%", contentFont5));
            cellg03.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellg03.setBackgroundColor(colorHeader2);
            table3.addCell(cellg03);

            //document.add(table3);
            PdfPTable mainTable = new PdfPTable(2);
            mainTable.setWidthPercentage(100);

            PdfPCell cell1main = new PdfPCell();
            cell1main.setBorder(Rectangle.NO_BORDER); // Eliminar el borde de la celda
            cell1main.addElement(table3); // Agregar la primera tabla a la celda
            mainTable.addCell(cell1main);

            // Crear la segunda tabla que irá a la derecha de la primera
            PdfPTable table4 = new PdfPTable(2); // Definir una nueva tabla con 2 columnas
            // Agregar contenido a la segunda tabla (ejemplo)
            PdfPCell cell2_1 = new PdfPCell(new Paragraph("Calificación comportamiento ventas", contentFont5));
            cell2_1.setBackgroundColor(colorHeader);
            cell2_1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell2_1);
            PdfPCell cell2_2 = new PdfPCell(new Paragraph("Calificacion", contentFont5));
            cell2_2.setBackgroundColor(colorHeader);
            cell2_2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell2_2);

            PdfPCell cell2_1a = new PdfPCell(new Paragraph("Muy Alto", contentFont5));
            cell2_1a.setBackgroundColor(colorHeader2);
            cell2_1a.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell2_1a);
            PdfPCell cell2_2a = new PdfPCell(new Paragraph("9", contentFont5));
            cell2_2a.setBackgroundColor(colorHeader2);
            cell2_2a.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell2_2a);

            PdfPCell cell2_1b = new PdfPCell(new Paragraph("Alto", contentFont5));
            cell2_1b.setBackgroundColor(colorHeader2);
            cell2_1b.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell2_1b);
            PdfPCell cell2_2b = new PdfPCell(new Paragraph("7", contentFont5));
            cell2_2b.setBackgroundColor(colorHeader2);
            cell2_2b.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell2_2b);

            PdfPCell cell2_1c = new PdfPCell(new Paragraph("Normal", contentFont5));
            cell2_1c.setBackgroundColor(colorHeader2);
            cell2_1c.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell2_1c);
            PdfPCell cell2_2c = new PdfPCell(new Paragraph("5", contentFont5));
            cell2_2c.setBackgroundColor(colorHeader2);
            cell2_2c.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell2_2c);

            PdfPCell cell2_1d = new PdfPCell(new Paragraph("Bajo", contentFont5));
            cell2_1d.setBackgroundColor(colorHeader2);
            cell2_1d.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell2_1d);
            PdfPCell cell2_2d = new PdfPCell(new Paragraph("3", contentFont5));
            cell2_2d.setBackgroundColor(colorHeader2);
            cell2_2d.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell2_2d);

            // Segunda celda de la tabla principal para la segunda tabla
            PdfPCell cell2 = new PdfPCell();
            cell2.setBorder(Rectangle.NO_BORDER); // Eliminar el borde de la celda
            cell2.addElement(table4); // Agregar la segunda tabla a la celda
            mainTable.addCell(cell2);

            // Agregar la tabla principal al documento
            document.add(mainTable);

            document.newPage();
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));

            PdfPTable table5 = new PdfPTable(4);
            table5.setWidths(new float[]{25f, 25f, 25f, 25f});
            table5.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell cellb01a = new PdfPCell(new Paragraph("De acuerdo a la estacionalidad de su portafolio\n+"
                    + "califique los meses segun la tabla anterior", contentFont5));
            cellb01a.setColspan(2);
            cellb01a.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellb01a.setBackgroundColor(colorHeader);
            table5.addCell(cellb01a);

            PdfPCell cellb02a = new PdfPCell(new Paragraph("Año 1 Unidades", contentFont5));
            cellb02a.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellb02a.setBackgroundColor(colorHeader);
            table5.addCell(cellb02a);

            PdfPCell cellb03a = new PdfPCell(new Paragraph("Año 1 Ventas $", contentFont5));
            cellb03a.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellb03a.setBackgroundColor(colorHeader);
            table5.addCell(cellb03a);

            document.add(table5);

            document.close();
            outputStream.flush();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            // Manejar errores aquí
        }

    }

    private static class PdfHeaderEventHandler1 extends PdfPageEventHelper {

        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            try {
                // Obtener el número de página actual
                int pageNumber = writer.getPageNumber();

                // Obtener el contenido directo de la página
                PdfContentByte content = writer.getDirectContent();

                // Agregar imagen en el encabezado
                String imagePathIzq = new File("ImagenCamaraComercioIzquierda.jpg").getAbsolutePath();
                Image headerImageIzq = Image.getInstance(imagePathIzq);
                headerImageIzq.scaleToFit(150, headerImageIzq.getHeight());
                float imageX1 = 36;
                float imageY1 = PageSize.A4.rotate().getHeight() - 20 - headerImageIzq.getScaledHeight();
                headerImageIzq.setAbsolutePosition(imageX1, imageY1);
                content.addImage(headerImageIzq);

                String imagePathDer = new File("CodigoRCP-HerramientaDiagnostico.jpg").getAbsolutePath();
                Image headerImageDer = Image.getInstance(imagePathDer);
                headerImageDer.scaleToFit(100, 100);
                float imageX2 = PageSize.A4.rotate().getWidth() - 136;
                float imageY2 = PageSize.A4.rotate().getHeight() - 20 - headerImageDer.getScaledHeight();
                headerImageDer.setAbsolutePosition(imageX2, imageY2);
                content.addImage(headerImageDer);

                //Imagen Derecha pero a la izquierda de la imagen del metodo de abajo
                String imagePathIzq3 = new File("ImagenCDE_Empresarial.jpg").getAbsolutePath();
                Image headerImageIzq3 = Image.getInstance(imagePathIzq3);
                headerImageIzq3.scaleToFit(150, 150); // Ajusta el tamaño de la tercera imagen según tus necesidades
                float imageX3 = imageX2 - headerImageIzq3.getScaledWidth() - 10; // Coloca la tercera imagen a la izquierda de la segunda imagen
                float imageY3 = imageY2 - 10; // Mantiene la misma altura que la segunda imagen
                headerImageIzq3.setAbsolutePosition(imageX3, imageY3);
                content.addImage(headerImageIzq3);

            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }
        }
    }

}
