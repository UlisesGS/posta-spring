/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.posta.crm.entity.Client;
import com.posta.crm.entity.ProcessEmpresario;
import com.posta.crm.entity.SelfAssessment;
import com.posta.crm.entity.canvas.CanvasModel;
import com.posta.crm.entity.canvas.CostComponent;
import com.posta.crm.entity.empresario.ConceptosGenerales;
import com.posta.crm.enums.Answer;
import com.posta.crm.repository.ClientRepository;
import com.posta.crm.repository.SelfAssessmentRepository;
import com.posta.crm.repository.canvas.CanvasModelRepository;
import com.posta.crm.repository.empresario.ProcessEmpresarioRepository;
import com.posta.crm.service.canvas.process.IProcessService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
@RequestMapping("/pdf1")
@CrossOrigin(origins = "*")
public class PDFEmpreContro {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProcessEmpresarioRepository processEmpresarioRepository;
    @Autowired
    private IProcessService processService;

    @GetMapping("/planAccion/{id}")
    public void generarDiagnostico(HttpServletResponse response, @PathVariable Long id) throws DocumentException, IOException {
        String[] nombregestion = new String[6];
        nombregestion[0] = "LINEAMIENTOS BASICOS ESTRATEGICOS E IMAGEN COORPORATIVA";
        nombregestion[1] = "MERCADEO Y VENTAS";
        nombregestion[2] = "CONTABLE, FINANCIERO Y FONDOS";
        nombregestion[3] = "TALENTO HUMANO Y CAPACITACIONES";
        nombregestion[4] = "REGISTRO, MARCAS Y PERMISOS";
        nombregestion[5] = "OTROS COMPONENTES NO CONTEMPLADOS";

        // Establecer el tipo de contenido de la respuesta como PDF
        response.setContentType("application/pdf");
        // Establecer el encabezado para indicar la descarga del archivo PDF
        response.setHeader("Content-Disposition", "attachment; filename=planAccion.pdf");

        try {

            // Crear una instancia del documento y del escritor
            Document document = new Document(PageSize.A4);
            OutputStream outputStream = response.getOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.setMargins(document.leftMargin(), document.rightMargin(), 100, 100);

            // Crear una instancia de la clase PdfHeaderEventHandler para manejar el encabezado
            PDFEmpreContro.PdfHeaderEventHandler1 headerHandler = new PDFEmpreContro.PdfHeaderEventHandler1();
            writer.setPageEvent(headerHandler);

            // Abrir el documento para escribir el contenido
            document.open();

            Client client = clientRepository.findById(id).get();
            ProcessEmpresario processEmpresario = processEmpresarioRepository.findByClient(client.getId());
            // Agregar contenido al PDF
            // Crear un párrafo vacío antes del título para agregar espacio entre el título y el primer párrafo
            //Crear Fecha para portada
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
            String formattedDate = dateFormat.format(currentDate).toUpperCase();

            Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.BOLD);
            Paragraph title = new Paragraph(client.getCompanyName().toUpperCase(), titleFont);
            title.setSpacingBefore(20f);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));
            Paragraph title1 = new Paragraph("Plan de Acción", titleFont);

            title1.setAlignment(Element.ALIGN_CENTER);
            document.add(title1);
            document.add(new Paragraph("\n"));
            // Aquí deberías obtener los datos de tu base de datos, si los necesitas
            // Establecer el estilo de fuente para el contenido
            Font contentFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD);
            //Font atributos = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.DARK_GRAY);
            Font contentFont1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
            Font contentFont2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
            Font contentFont3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
            Font contentFont4 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.NORMAL);

            //Lineamientos Basicos
            for (int i = 0; i < 1; i++) {
                //Lineamientos Basicos

                //Crea tabla con 3 columnas
                PdfPTable table = new PdfPTable(3);
                table.setWidths(new float[]{80f, 15f, 15f});

                BaseColor colorHeader = new BaseColor(220, 220, 220);
                //Agrega el Titulo a la fila
                PdfPCell cell = new PdfPCell(new Paragraph(nombregestion[0], contentFont4));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(colorHeader);
                table.addCell(cell);

                PdfPCell cell1 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getLineamientosBasicos().getFechaInicio().toString(), contentFont4));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setBackgroundColor(colorHeader);
                table.addCell(cell1);

                PdfPCell cell2 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getLineamientosBasicos().getFechaCierre().toString(), contentFont4));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setBackgroundColor(colorHeader);
                table.addCell(cell2);

                PdfPTable segundaFila = new PdfPTable(2);
                segundaFila.setWidths(new float[]{20f, 80f});

                PdfPCell cell3 = new PdfPCell(new Paragraph("Objetivo Estratégico", contentFont4));
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila.addCell(cell3);

                PdfPCell cell30 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getLineamientosBasicos().getObjetivoEstrategico(), contentFont4));
                cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila.addCell(cell30);

                PdfPCell cellSegundaFila = new PdfPCell(segundaFila);
                cellSegundaFila.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila);

                PdfPTable segundaFila1 = new PdfPTable(2);
                segundaFila1.setWidths(new float[]{20f, 80f});

                PdfPCell cell4 = new PdfPCell(new Paragraph("Actividad Estratégica", contentFont4));
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila1.addCell(cell4);

                PdfPCell cell40 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getLineamientosBasicos().getActividadEstrategica(), contentFont4));
                cell40.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila1.addCell(cell40);

                PdfPCell cellSegundaFila1 = new PdfPCell(segundaFila1);
                cellSegundaFila1.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila1);

                PdfPTable segundaFila2 = new PdfPTable(2);
                segundaFila2.setWidths(new float[]{20f, 80f});

                PdfPCell cell5 = new PdfPCell(new Paragraph("Responsable", contentFont4));
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila2.addCell(cell5);

                PdfPCell cell50 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getLineamientosBasicos().getResponsable(), contentFont4));
                cell50.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila2.addCell(cell50);

                PdfPCell cellSegundaFila2 = new PdfPCell(segundaFila2);
                cellSegundaFila2.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila2);

                PdfPTable segundaFila3 = new PdfPTable(2);
                segundaFila3.setWidths(new float[]{20f, 80f});

                PdfPCell cell6 = new PdfPCell(new Paragraph("Aliados Estratégicos", contentFont4));
                cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila3.addCell(cell6);

                PdfPCell cell60 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getLineamientosBasicos().getAliadosEstrategicos(), contentFont4));
                cell60.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila3.addCell(cell60);

                PdfPCell cellSegundaFila3 = new PdfPCell(segundaFila3);
                cellSegundaFila3.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila3);

                PdfPTable segundaFila4 = new PdfPTable(2);
                segundaFila4.setWidths(new float[]{20f, 80f});

                PdfPCell cell7 = new PdfPCell(new Paragraph("Cumpliento", contentFont4));
                cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila4.addCell(cell7);

                PdfPCell cell70 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getLineamientosBasicos().getCumplimiento(), contentFont4));
                cell70.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila4.addCell(cell70);

                PdfPCell cellSegundaFila4 = new PdfPCell(segundaFila4);
                cellSegundaFila4.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila4);

                PdfPTable segundaFila5 = new PdfPTable(2);
                segundaFila5.setWidths(new float[]{20f, 80f});

                PdfPCell cell8 = new PdfPCell(new Paragraph("Observaciones", contentFont4));
                cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila5.addCell(cell8);

                PdfPCell cell80 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getLineamientosBasicos().getObservaciones(), contentFont4));
                cell80.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila5.addCell(cell80);

                PdfPCell cellSegundaFila5 = new PdfPCell(segundaFila5);
                cellSegundaFila5.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila5);

                document.add(table);
            }

            document.add(Chunk.NEWLINE);
            
            //Mercadeo y Ventas
            for (int i = 0; i < 1; i++) {

                //Crea tabla con 3 columnas
                PdfPTable table = new PdfPTable(3);
                table.setWidths(new float[]{80f, 15f, 15f});

                BaseColor colorHeader = new BaseColor(220, 220, 220);
                //Agrega el Titulo a la fila
                PdfPCell cell = new PdfPCell(new Paragraph(nombregestion[1], contentFont4));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(colorHeader);
                table.addCell(cell);

                PdfPCell cell1 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getMercadeoVentas().getFechaInicio().toString(), contentFont4));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setBackgroundColor(colorHeader);
                table.addCell(cell1);

                PdfPCell cell2 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getMercadeoVentas().getFechaCierre().toString(), contentFont4));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setBackgroundColor(colorHeader);
                table.addCell(cell2);

                PdfPTable segundaFila = new PdfPTable(2);
                segundaFila.setWidths(new float[]{20f, 80f});

                PdfPCell cell3 = new PdfPCell(new Paragraph("Objetivo Estratégico", contentFont4));
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila.addCell(cell3);

                PdfPCell cell30 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getMercadeoVentas().getObjetivoEstrategico(), contentFont4));
                cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila.addCell(cell30);

                PdfPCell cellSegundaFila = new PdfPCell(segundaFila);
                cellSegundaFila.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila);

                PdfPTable segundaFila1 = new PdfPTable(2);
                segundaFila1.setWidths(new float[]{20f, 80f});

                PdfPCell cell4 = new PdfPCell(new Paragraph("Actividad Estratégica", contentFont4));
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila1.addCell(cell4);

                PdfPCell cell40 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getMercadeoVentas().getActividadEstrategica(), contentFont4));
                cell40.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila1.addCell(cell40);

                PdfPCell cellSegundaFila1 = new PdfPCell(segundaFila1);
                cellSegundaFila1.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila1);

                PdfPTable segundaFila2 = new PdfPTable(2);
                segundaFila2.setWidths(new float[]{20f, 80f});

                PdfPCell cell5 = new PdfPCell(new Paragraph("Responsable", contentFont4));
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila2.addCell(cell5);

                PdfPCell cell50 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getMercadeoVentas().getResponsable(), contentFont4));
                cell50.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila2.addCell(cell50);

                PdfPCell cellSegundaFila2 = new PdfPCell(segundaFila2);
                cellSegundaFila2.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila2);

                PdfPTable segundaFila3 = new PdfPTable(2);
                segundaFila3.setWidths(new float[]{20f, 80f});

                PdfPCell cell6 = new PdfPCell(new Paragraph("Aliados Estratégicos", contentFont4));
                cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila3.addCell(cell6);

                PdfPCell cell60 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getMercadeoVentas().getAliadosEstrategicos(), contentFont4));
                cell60.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila3.addCell(cell60);

                PdfPCell cellSegundaFila3 = new PdfPCell(segundaFila3);
                cellSegundaFila3.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila3);

                PdfPTable segundaFila4 = new PdfPTable(2);
                segundaFila4.setWidths(new float[]{20f, 80f});

                PdfPCell cell7 = new PdfPCell(new Paragraph("Cumpliento", contentFont4));
                cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila4.addCell(cell7);

                PdfPCell cell70 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getMercadeoVentas().getCumplimiento(), contentFont4));
                cell70.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila4.addCell(cell70);

                PdfPCell cellSegundaFila4 = new PdfPCell(segundaFila4);
                cellSegundaFila4.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila4);

                PdfPTable segundaFila5 = new PdfPTable(2);
                segundaFila5.setWidths(new float[]{20f, 80f});

                PdfPCell cell8 = new PdfPCell(new Paragraph("Observaciones", contentFont4));
                cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila5.addCell(cell8);

                PdfPCell cell80 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getMercadeoVentas().getObservaciones(), contentFont4));
                cell80.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila5.addCell(cell80);

                PdfPCell cellSegundaFila5 = new PdfPCell(segundaFila5);
                cellSegundaFila5.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila5);

                document.add(table);
            }
            
            document.add(Chunk.NEWLINE);

            //COntabel Financiero y Fondos
            for (int i = 0; i < 1; i++) {

                //Crea tabla con 3 columnas
                PdfPTable table = new PdfPTable(3);
                table.setWidths(new float[]{80f, 15f, 15f});

                BaseColor colorHeader = new BaseColor(220, 220, 220);
                //Agrega el Titulo a la fila
                PdfPCell cell = new PdfPCell(new Paragraph(nombregestion[2], contentFont4));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(colorHeader);
                table.addCell(cell);

                PdfPCell cell1 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getProduccionOperaciones().getFechaInicio().toString(), contentFont4));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setBackgroundColor(colorHeader);
                table.addCell(cell1);

                PdfPCell cell2 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getProduccionOperaciones().getFechaCierre().toString(), contentFont4));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setBackgroundColor(colorHeader);
                table.addCell(cell2);

                PdfPTable segundaFila = new PdfPTable(2);
                segundaFila.setWidths(new float[]{20f, 80f});

                PdfPCell cell3 = new PdfPCell(new Paragraph("Objetivo Estratégico", contentFont4));
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila.addCell(cell3);

                PdfPCell cell30 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getProduccionOperaciones().getObjetivoEstrategico(), contentFont4));
                cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila.addCell(cell30);

                PdfPCell cellSegundaFila = new PdfPCell(segundaFila);
                cellSegundaFila.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila);

                PdfPTable segundaFila1 = new PdfPTable(2);
                segundaFila1.setWidths(new float[]{20f, 80f});

                PdfPCell cell4 = new PdfPCell(new Paragraph("Actividad Estratégica", contentFont4));
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila1.addCell(cell4);

                PdfPCell cell40 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getProduccionOperaciones().getActividadEstrategica(), contentFont4));
                cell40.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila1.addCell(cell40);

                PdfPCell cellSegundaFila1 = new PdfPCell(segundaFila1);
                cellSegundaFila1.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila1);

                PdfPTable segundaFila2 = new PdfPTable(2);
                segundaFila2.setWidths(new float[]{20f, 80f});

                PdfPCell cell5 = new PdfPCell(new Paragraph("Responsable", contentFont4));
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila2.addCell(cell5);

                PdfPCell cell50 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getProduccionOperaciones().getResponsable(), contentFont4));
                cell50.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila2.addCell(cell50);

                PdfPCell cellSegundaFila2 = new PdfPCell(segundaFila2);
                cellSegundaFila2.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila2);

                PdfPTable segundaFila3 = new PdfPTable(2);
                segundaFila3.setWidths(new float[]{20f, 80f});

                PdfPCell cell6 = new PdfPCell(new Paragraph("Aliados Estratégicos", contentFont4));
                cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila3.addCell(cell6);

                PdfPCell cell60 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getProduccionOperaciones().getAliadosEstrategicos(), contentFont4));
                cell60.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila3.addCell(cell60);

                PdfPCell cellSegundaFila3 = new PdfPCell(segundaFila3);
                cellSegundaFila3.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila3);

                PdfPTable segundaFila4 = new PdfPTable(2);
                segundaFila4.setWidths(new float[]{20f, 80f});

                PdfPCell cell7 = new PdfPCell(new Paragraph("Cumpliento", contentFont4));
                cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila4.addCell(cell7);

                PdfPCell cell70 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getProduccionOperaciones().getCumplimiento(), contentFont4));
                cell70.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila4.addCell(cell70);

                PdfPCell cellSegundaFila4 = new PdfPCell(segundaFila4);
                cellSegundaFila4.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila4);

                PdfPTable segundaFila5 = new PdfPTable(2);
                segundaFila5.setWidths(new float[]{20f, 80f});

                PdfPCell cell8 = new PdfPCell(new Paragraph("Observaciones", contentFont4));
                cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila5.addCell(cell8);

                PdfPCell cell80 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getProduccionOperaciones().getObservaciones(), contentFont4));
                cell80.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila5.addCell(cell80);

                PdfPCell cellSegundaFila5 = new PdfPCell(segundaFila5);
                cellSegundaFila5.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila5);

                document.add(table);
            }
            
            document.add(Chunk.NEWLINE);

            //Talento Humano
            for (int i = 0; i < 1; i++) {

                //Crea tabla con 3 columnas
                PdfPTable table = new PdfPTable(3);
                table.setWidths(new float[]{80f, 15f, 15f});

                BaseColor colorHeader = new BaseColor(220, 220, 220);
                //Agrega el Titulo a la fila
                PdfPCell cell = new PdfPCell(new Paragraph(nombregestion[3], contentFont4));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(colorHeader);
                table.addCell(cell);

                PdfPCell cell1 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getTalentoHumano().getFechaInicio().toString(), contentFont4));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setBackgroundColor(colorHeader);
                table.addCell(cell1);

                PdfPCell cell2 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getTalentoHumano().getFechaCierre().toString(), contentFont4));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setBackgroundColor(colorHeader);
                table.addCell(cell2);

                PdfPTable segundaFila = new PdfPTable(2);
                segundaFila.setWidths(new float[]{20f, 80f});

                PdfPCell cell3 = new PdfPCell(new Paragraph("Objetivo Estratégico", contentFont4));
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila.addCell(cell3);

                PdfPCell cell30 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getTalentoHumano().getObjetivoEstrategico(), contentFont4));
                cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila.addCell(cell30);

                PdfPCell cellSegundaFila = new PdfPCell(segundaFila);
                cellSegundaFila.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila);

                PdfPTable segundaFila1 = new PdfPTable(2);
                segundaFila1.setWidths(new float[]{20f, 80f});

                PdfPCell cell4 = new PdfPCell(new Paragraph("Actividad Estratégica", contentFont4));
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila1.addCell(cell4);

                PdfPCell cell40 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getTalentoHumano().getActividadEstrategica(), contentFont4));
                cell40.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila1.addCell(cell40);

                PdfPCell cellSegundaFila1 = new PdfPCell(segundaFila1);
                cellSegundaFila1.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila1);

                PdfPTable segundaFila2 = new PdfPTable(2);
                segundaFila2.setWidths(new float[]{20f, 80f});

                PdfPCell cell5 = new PdfPCell(new Paragraph("Responsable", contentFont4));
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila2.addCell(cell5);

                PdfPCell cell50 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getTalentoHumano().getResponsable(), contentFont4));
                cell50.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila2.addCell(cell50);

                PdfPCell cellSegundaFila2 = new PdfPCell(segundaFila2);
                cellSegundaFila2.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila2);

                PdfPTable segundaFila3 = new PdfPTable(2);
                segundaFila3.setWidths(new float[]{20f, 80f});

                PdfPCell cell6 = new PdfPCell(new Paragraph("Aliados Estratégicos", contentFont4));
                cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila3.addCell(cell6);

                PdfPCell cell60 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getTalentoHumano().getAliadosEstrategicos(), contentFont4));
                cell60.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila3.addCell(cell60);

                PdfPCell cellSegundaFila3 = new PdfPCell(segundaFila3);
                cellSegundaFila3.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila3);

                PdfPTable segundaFila4 = new PdfPTable(2);
                segundaFila4.setWidths(new float[]{20f, 80f});

                PdfPCell cell7 = new PdfPCell(new Paragraph("Cumpliento", contentFont4));
                cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila4.addCell(cell7);

                PdfPCell cell70 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getTalentoHumano().getCumplimiento(), contentFont4));
                cell70.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila4.addCell(cell70);

                PdfPCell cellSegundaFila4 = new PdfPCell(segundaFila4);
                cellSegundaFila4.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila4);

                PdfPTable segundaFila5 = new PdfPTable(2);
                segundaFila5.setWidths(new float[]{20f, 80f});

                PdfPCell cell8 = new PdfPCell(new Paragraph("Observaciones", contentFont4));
                cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila5.addCell(cell8);

                PdfPCell cell80 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getTalentoHumano().getObservaciones(), contentFont4));
                cell80.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila5.addCell(cell80);

                PdfPCell cellSegundaFila5 = new PdfPCell(segundaFila5);
                cellSegundaFila5.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila5);

                document.add(table);
            }
            
            document.add(Chunk.NEWLINE);
            
            //Registo Marcas y Permisos
            for (int i = 0; i < 1; i++) {

                //Crea tabla con 3 columnas
                PdfPTable table = new PdfPTable(3);
                table.setWidths(new float[]{80f, 15f, 15f});

                BaseColor colorHeader = new BaseColor(220, 220, 220);
                //Agrega el Titulo a la fila
                PdfPCell cell = new PdfPCell(new Paragraph(nombregestion[4], contentFont4));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(colorHeader);
                table.addCell(cell);

                PdfPCell cell1 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getRegistroMarcas().getFechaInicio().toString(), contentFont4));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setBackgroundColor(colorHeader);
                table.addCell(cell1);

                PdfPCell cell2 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getRegistroMarcas().getFechaCierre().toString(), contentFont4));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setBackgroundColor(colorHeader);
                table.addCell(cell2);

                PdfPTable segundaFila = new PdfPTable(2);
                segundaFila.setWidths(new float[]{20f, 80f});

                PdfPCell cell3 = new PdfPCell(new Paragraph("Objetivo Estratégico", contentFont4));
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila.addCell(cell3);

                PdfPCell cell30 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getRegistroMarcas().getObjetivoEstrategico(), contentFont4));
                cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila.addCell(cell30);

                PdfPCell cellSegundaFila = new PdfPCell(segundaFila);
                cellSegundaFila.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila);

                PdfPTable segundaFila1 = new PdfPTable(2);
                segundaFila1.setWidths(new float[]{20f, 80f});

                PdfPCell cell4 = new PdfPCell(new Paragraph("Actividad Estratégica", contentFont4));
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila1.addCell(cell4);

                PdfPCell cell40 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getRegistroMarcas().getActividadEstrategica(), contentFont4));
                cell40.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila1.addCell(cell40);

                PdfPCell cellSegundaFila1 = new PdfPCell(segundaFila1);
                cellSegundaFila1.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila1);

                PdfPTable segundaFila2 = new PdfPTable(2);
                segundaFila2.setWidths(new float[]{20f, 80f});

                PdfPCell cell5 = new PdfPCell(new Paragraph("Responsable", contentFont4));
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila2.addCell(cell5);

                PdfPCell cell50 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getRegistroMarcas().getResponsable(), contentFont4));
                cell50.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila2.addCell(cell50);

                PdfPCell cellSegundaFila2 = new PdfPCell(segundaFila2);
                cellSegundaFila2.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila2);

                PdfPTable segundaFila3 = new PdfPTable(2);
                segundaFila3.setWidths(new float[]{20f, 80f});

                PdfPCell cell6 = new PdfPCell(new Paragraph("Aliados Estratégicos", contentFont4));
                cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila3.addCell(cell6);

                PdfPCell cell60 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getRegistroMarcas().getAliadosEstrategicos(), contentFont4));
                cell60.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila3.addCell(cell60);

                PdfPCell cellSegundaFila3 = new PdfPCell(segundaFila3);
                cellSegundaFila3.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila3);

                PdfPTable segundaFila4 = new PdfPTable(2);
                segundaFila4.setWidths(new float[]{20f, 80f});

                PdfPCell cell7 = new PdfPCell(new Paragraph("Cumpliento", contentFont4));
                cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila4.addCell(cell7);

                PdfPCell cell70 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getRegistroMarcas().getCumplimiento(), contentFont4));
                cell70.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila4.addCell(cell70);

                PdfPCell cellSegundaFila4 = new PdfPCell(segundaFila4);
                cellSegundaFila4.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila4);

                PdfPTable segundaFila5 = new PdfPTable(2);
                segundaFila5.setWidths(new float[]{20f, 80f});

                PdfPCell cell8 = new PdfPCell(new Paragraph("Observaciones", contentFont4));
                cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila5.addCell(cell8);

                PdfPCell cell80 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getRegistroMarcas().getObservaciones(), contentFont4));
                cell80.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila5.addCell(cell80);

                PdfPCell cellSegundaFila5 = new PdfPCell(segundaFila5);
                cellSegundaFila5.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila5);

                document.add(table);
            }
            
            document.add(Chunk.NEWLINE);
            
            //Otros componente no contempladdos
            for (int i = 0; i < 1; i++) {

                //Crea tabla con 3 columnas
                PdfPTable table = new PdfPTable(3);
                table.setWidths(new float[]{80f, 15f, 15f});

                BaseColor colorHeader = new BaseColor(220, 220, 220);
                //Agrega el Titulo a la fila
                PdfPCell cell = new PdfPCell(new Paragraph(nombregestion[5], contentFont4));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(colorHeader);
                table.addCell(cell);

                PdfPCell cell1 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getNoContemplados().getFechaInicio().toString(), contentFont4));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setBackgroundColor(colorHeader);
                table.addCell(cell1);

                PdfPCell cell2 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getNoContemplados().getFechaCierre().toString(), contentFont4));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setBackgroundColor(colorHeader);
                table.addCell(cell2);

                PdfPTable segundaFila = new PdfPTable(2);
                segundaFila.setWidths(new float[]{20f, 80f});

                PdfPCell cell3 = new PdfPCell(new Paragraph("Objetivo Estratégico", contentFont4));
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila.addCell(cell3);

                PdfPCell cell30 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getNoContemplados().getObjetivoEstrategico(), contentFont4));
                cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila.addCell(cell30);

                PdfPCell cellSegundaFila = new PdfPCell(segundaFila);
                cellSegundaFila.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila);

                PdfPTable segundaFila1 = new PdfPTable(2);
                segundaFila1.setWidths(new float[]{20f, 80f});

                PdfPCell cell4 = new PdfPCell(new Paragraph("Actividad Estratégica", contentFont4));
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila1.addCell(cell4);

                PdfPCell cell40 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getNoContemplados().getActividadEstrategica(), contentFont4));
                cell40.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila1.addCell(cell40);

                PdfPCell cellSegundaFila1 = new PdfPCell(segundaFila1);
                cellSegundaFila1.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila1);

                PdfPTable segundaFila2 = new PdfPTable(2);
                segundaFila2.setWidths(new float[]{20f, 80f});

                PdfPCell cell5 = new PdfPCell(new Paragraph("Responsable", contentFont4));
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila2.addCell(cell5);

                PdfPCell cell50 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getNoContemplados().getResponsable(), contentFont4));
                cell50.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila2.addCell(cell50);

                PdfPCell cellSegundaFila2 = new PdfPCell(segundaFila2);
                cellSegundaFila2.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila2);

                PdfPTable segundaFila3 = new PdfPTable(2);
                segundaFila3.setWidths(new float[]{20f, 80f});

                PdfPCell cell6 = new PdfPCell(new Paragraph("Aliados Estratégicos", contentFont4));
                cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila3.addCell(cell6);

                PdfPCell cell60 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getNoContemplados().getAliadosEstrategicos(), contentFont4));
                cell60.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila3.addCell(cell60);

                PdfPCell cellSegundaFila3 = new PdfPCell(segundaFila3);
                cellSegundaFila3.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila3);

                PdfPTable segundaFila4 = new PdfPTable(2);
                segundaFila4.setWidths(new float[]{20f, 80f});

                PdfPCell cell7 = new PdfPCell(new Paragraph("Cumpliento", contentFont4));
                cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila4.addCell(cell7);

                PdfPCell cell70 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getNoContemplados().getCumplimiento(), contentFont4));
                cell70.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila4.addCell(cell70);

                PdfPCell cellSegundaFila4 = new PdfPCell(segundaFila4);
                cellSegundaFila4.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila4);

                PdfPTable segundaFila5 = new PdfPTable(2);
                segundaFila5.setWidths(new float[]{20f, 80f});

                PdfPCell cell8 = new PdfPCell(new Paragraph("Observaciones", contentFont4));
                cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila5.addCell(cell8);

                PdfPCell cell80 = new PdfPCell(new Paragraph(processEmpresario.getPlanDeAccion().getNoContemplados().getObservaciones(), contentFont4));
                cell80.setHorizontalAlignment(Element.ALIGN_CENTER);
                segundaFila5.addCell(cell80);

                PdfPCell cellSegundaFila5 = new PdfPCell(segundaFila5);
                cellSegundaFila5.setColspan(3); // Para ocupar las dos últimas columnas
                table.addCell(cellSegundaFila5);

                document.add(table);
            }
            
            document.add(Chunk.NEWLINE);

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
                float imageY1 = PageSize.A4.getHeight() - 20 - headerImageIzq.getScaledHeight();
                headerImageIzq.setAbsolutePosition(imageX1, imageY1);
                content.addImage(headerImageIzq);

                String imagePathDer = new File("CodigoRCP-HerramientaDiagnostico.jpg").getAbsolutePath();
                Image headerImageDer = Image.getInstance(imagePathDer);
                headerImageDer.scaleToFit(100, 100);
                float imageX2 = PageSize.A4.getWidth() - 136;
                float imageY2 = PageSize.A4.getHeight() - 20 - headerImageDer.getScaledHeight();
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
