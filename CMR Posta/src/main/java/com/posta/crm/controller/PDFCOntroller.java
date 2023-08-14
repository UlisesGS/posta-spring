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
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.posta.crm.entity.Client;
import com.posta.crm.entity.ProcessEmpresario;
import com.posta.crm.repository.ClientRepository;
import com.posta.crm.repository.empresario.ProcessEmpresarioRepository;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("/pdf")
@CrossOrigin(origins = "*")
public class PDFCOntroller {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProcessEmpresarioRepository processEmpresarioRepository;

    @GetMapping("/generarPdf/{id}")
    public void generarPDF(HttpServletResponse response, @PathVariable Long id) {
        // Establecer el tipo de contenido de la respuesta como PDF
        response.setContentType("application/pdf");
        // Establecer el encabezado para indicar la descarga del archivo PDF
        response.setHeader("Content-Disposition", "attachment; filename=example.pdf");

        try {
            // Crear un nuevo documento PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());

            // Abrir el documento para agregar contenido
            document.open();

            String imagePathIzq = new File("camaraHD.jpg").getAbsolutePath();

            // Agregar la imagen en la parte superior izquierda
            Image image = Image.getInstance(imagePathIzq);
            image.scaleToFit(100, 100); // Escala la imagen para que tenga un ancho y alto de 100 unidades
            image.setAbsolutePosition(36, 750); // Coordenadas (36, 770) en el PDF (medida en unidades, donde 1 unidad = 1/72 pulgadas)
            document.add(image);

            String imagePathDer = new File("costitaIzq.jpg").getAbsolutePath();

            // Agregar la imagen en la parte superior izquierda
            Image image1 = Image.getInstance(imagePathDer);
            image1.scaleToFit(100, 100); // Escala la imagen para que tenga un ancho y alto de 100 unidades
            image1.setAbsolutePosition(PageSize.A4.getWidth() - 136, 760); // Coordenadas (36, 770) en el PDF (medida en unidades, donde 1 unidad = 1/72 pulgadas)
            document.add(image1);

            // Agregar contenido al PDF
            // Crear un párrafo vacío antes del título para agregar espacio entre el título y el primer párrafo
            // Establecer el estilo de fuente para el título
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD);
            Paragraph title = new Paragraph("Datos del Cliente", titleFont);
            title.setSpacingBefore(20f);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n\n"));

            // Aquí deberías obtener los datos de tu base de datos, si los necesitas
            Client client = clientRepository.findById(id).get();
            // Establecer el estilo de fuente para el contenido
            Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
            Font atributos = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.DARK_GRAY);
            String tipo;
            String tipoNego;
            String tipoServ;
            if (client.getType().equals("businessman")) {
                tipo = "EMPRESARIO";
            } else {
                tipo = "EMPRENDEDOR";
            }
            if (client.getType().equals("businessman")) {
                tipoNego = client.getCompanyName();
            } else {
                tipoNego = client.getBusinessIdea();
            }
            if (client.getType().equals("businessman")) {
                tipoServ = client.getCiiu().getTitulo().toString();
            } else {
                tipoServ = client.getProduct();
            }
            // Simulamos datos de ejemplo para agregar al PDF
//            String nombre = "John Doe";
//            int edad = 30;
//            String email = "johndoe@example.com";

            // Crear un párrafo con los datos y agregarlo al PDF
            Paragraph paragraph = new Paragraph();
            paragraph.add(new Phrase("Clasificación de Cliente: ", contentFont));
            paragraph.add(new Phrase(tipo, atributos));
            paragraph.add(new Phrase("\nNombres y Apellidos: ", contentFont));
            paragraph.add(new Phrase(client.getName() + " " + client.getLastName() + " ", atributos));
            paragraph.add(new Phrase("                          Documento/NIT: ", contentFont));
            paragraph.add(new Phrase(String.valueOf(client.getNIT()), atributos));
            paragraph.add(new Phrase("\nGénero: ", contentFont));
            paragraph.add(new Phrase(client.getGender().name(), atributos));
            paragraph.add(new Phrase("\nNombre de la Empresa o Idea de Negocio: ", contentFont));
            paragraph.add(new Phrase(tipoNego, atributos));
            paragraph.add(new Phrase("\nProducto o Servicio a Comercializar: ", contentFont));
            paragraph.add(new Phrase(tipoServ, atributos));
            paragraph.add(new Phrase("\nN° Teléfono: ", contentFont));
            paragraph.add(new Phrase(client.getPhone(), atributos));
            paragraph.add(new Phrase("                          Correo Electrónico: ", contentFont));
            paragraph.add(new Phrase(client.getEmail(), atributos));

            paragraph.setSpacingAfter(10f); // Agregar espacio después del párrafo
            document.add(paragraph);

            // Cerrar el documento
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            // Manejar errores aquí
        }
    }

    @GetMapping("/informe/{id}")
    public void generarInforme(HttpServletResponse response, @PathVariable Long id) {
        // Establecer el tipo de contenido de la respuesta como PDF
        response.setContentType("application/pdf");
        // Establecer el encabezado para indicar la descarga del archivo PDF
        response.setHeader("Content-Disposition", "attachment; filename=informeEmpresarial.pdf");

        try {
            // Crear un nuevo documento PDF
            //Document document = new Document(PageSize.A4);//este es el funcional
            //PdfWriter writer=PdfWriter.getInstance(document, response.getOutputStream());
            // Abrir el documento para agregar contenido
            //document.open();

            // Crear una instancia del documento y del escritor
            Document document = new Document(PageSize.A4);
            OutputStream outputStream = response.getOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            // Crear una instancia de la clase PdfFooterEventHandler para manejar el pie de página
            PdfFooterEventHandler footerHandler = new PdfFooterEventHandler();
            writer.setPageEvent(footerHandler);
            // Crear una instancia de la clase PdfHeaderEventHandler para manejar el encabezado
            PdfHeaderEventHandler headerHandler = new PdfHeaderEventHandler();
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

            // Establecer el estilo de fuente para el título
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("\n\n"));
            Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.BOLD);
            Paragraph title = new Paragraph(client.getCompanyName().toUpperCase(), titleFont);
            title.setSpacingBefore(20f);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("\n\n"));
            // Aquí deberías obtener los datos de tu base de datos, si los necesitas
            // Establecer el estilo de fuente para el contenido
            Font contentFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD);
            //Font atributos = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.DARK_GRAY);
            Font contentFont1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
            Font contentFont2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
            Font contentFont3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);

            // Crear un párrafo con los datos y agregarlo al PDF
            Paragraph paragraph = new Paragraph();
            paragraph.add(new Phrase("Informe de Diagnóstico para Empresarios", contentFont));
            paragraph.add(new Phrase("\n\n\n\n\n\n" + client.getUser().getName().toUpperCase() + " " + client.getUser().getLastName().toUpperCase(), contentFont1));
            paragraph.add(new Phrase("\nASESOR/A CCV", contentFont1));
            paragraph.add(new Phrase("\n\n\n\n\n\nPROGRAMA DE FORTALECIMIENTO EMPRESARIAL", contentFont1));
            paragraph.add(new Phrase("\nCENTRO DE DESARROLLO EMPRESARIAL CDE-SBDC", contentFont1));
            paragraph.add(new Phrase("\nVILLAVICENCIO", contentFont1));
            paragraph.add(new Phrase("\n" + formattedDate, contentFont1));

            paragraph.setSpacingAfter(10f); // Agregar espacio después del párrafo
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            //Agregar Pagina nueva
            document.newPage();
            document.add(new Paragraph("\n\n"));
            Font titleFont1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD);
            Paragraph title2 = new Paragraph("CONTENIDO", titleFont1);
            title2.setSpacingBefore(20f);
            title2.setAlignment(Element.ALIGN_CENTER);
            document.add(title2);
            document.add(new Paragraph("\n\n"));
            Paragraph paragraph1 = new Paragraph();
            paragraph1.add(new Phrase("PRESENTACIÓN.......................................................................................3\n"
                    + "1. INFORMACION BÁSICA DE LA EMPRESA..............................................................................5\n"
                    + "     1.1 RESEÑA HISTÓRICA........................................................................................5\n"
                    + "2. INFORME DE DIANÓSTICO.........................................................................................6\n"
                    + "     2.2. DIAGNÓSTICO.............................................................................................6\n"
                    + "     2.2.1  AREA LINEAMIENTOS BÁSICOS ESTRATÉGICOS........................................6\n"
                    + "     2.2.2 AREA DE MERCADEO Y VENTAS..................................................................6\n"
                    + "     2.2.3 AREA PRODUCCIÓN Y OPERACIONES..........................................................6\n"
                    + "     2.2.4 AREA FINANCIERA.........................................................................................6\n"
                    + "     2.2.5 AREA TALENTO HUMANO...............................................................................6\n"
                    + "     2.2.6 NECESIDADES DE FORMACION (Si Aplica)..........................................................6\n"
                    + "     2.2.7 AREA ASOCIATIVIDAD (Si Aplica).......................................................................6\n"
                    + "     2.2.8 OTROS ASPECTOS A FORTALECER (Si Aplica).................................................7\n"
                    + "3. CONCLUSIONES Y RECOMENDACIONES.....................................................................7", contentFont2));

            paragraph1.setSpacingAfter(10f); // Agregar espacio después del párrafo
            paragraph1.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
            document.add(paragraph1);

            document.newPage();
            document.add(new Paragraph("\n\n"));

            Date currentDate1 = processEmpresario.getFechaAlta();
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
            String formattedDate1 = dateFormat1.format(currentDate1);

            Paragraph title3 = new Paragraph("PRESENTACIÓN", titleFont1);
            title3.setSpacingBefore(20f);
            title3.setAlignment(Element.ALIGN_CENTER);
            document.add(title3);
            document.add(new Paragraph("\n\n"));
            Paragraph paragraph3 = new Paragraph();
            paragraph3.add(new Phrase("El presente “INFORME DE DIAGNÓSTICO” corresponde a la empresa " + client.getCompanyName().toUpperCase()
                    + ", a la cual se aplicó la herramienta de diagnóstico el pasado " + formattedDate1 + ".", contentFont2));
            paragraph3.add(new Phrase("\n\nLa información aquí consignada se apoya en los datos obtenidos a través de las diferentes entrevistas"
                    + " sostenidas con el empresario y de los resultados arrojados por la herramienta de diagnóstico y su respectivo análisis."
                    + " Este diagnóstico apoyará el esfuerzo de la empresa y contribuirá a la construcción de una mejora en la toma de decisiones"
                    + " estratégicas, obtener mejores logros y desarrollar las siguientes fases del “Programa de Fortalecimiento Empresarial”."
                    + "  Midiendo el impacto económico en términos de ventas, empleo, productividad, competitividad y financiación, entre otros.\n"
                    + "El presente informe permitirá definir las estratégicas y su ejecución, orientadas a combatir las debilidades y amenazas identificadas,"
                    + " e implementar las soluciones tangibles que se requieran desde los siguientes elementos y perspectivas y algunas otras variables en los que se busca el aporte a la productividad y competitividad de su empresa: ", contentFont2));
            paragraph3.setLeading(20);
            paragraph3.setSpacingAfter(10f); // Agregar espacio después del párrafo
            paragraph3.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(paragraph3);

            Paragraph tabulatedParagraph = new Paragraph();
            tabulatedParagraph.setIndentationLeft(50);
            tabulatedParagraph.add(new Phrase("1.	AREA LINEAMIENTOS BASICOS ESTRATEGICOS DE LA EMPRESA:", contentFont3));
            tabulatedParagraph.add(new Phrase(" Este elemento permite identificar"
                    + " elementos básicos de la Planeación estratégica de la empresa; tales como su misión, visión, gestión organizacional, seguimiento"
                    + " a la gestión y sus procesos internos, toma de decisiones gerenciales entre otros, estos como elementos que permiten determinar"
                    + " un punto de partida y una meta clara para asegurar la sostenibilidad de la misma.", contentFont2));
            tabulatedParagraph.add(new Phrase("\n"));
            tabulatedParagraph.setIndentationLeft(50);
            tabulatedParagraph.add(new Phrase("\n2.	AREA DE MERCADEO Y VENTAS:", contentFont3));
            tabulatedParagraph.add(new Phrase(" Conjunto de procedimientos que buscan identificar la capacidad de"
                    + " la empresa en cuanto a que vender, a quienes vender, cuando vender, como vender, clientes, realizar promoción, fidelizar,"
                    + " captar y retener clientes.  Busca dar respuesta a la pregunta ¿Cómo nos ven los clientes? Mide el conocimiento de los"
                    + " clientes, de su(s) producto(s), y el posicionamiento de la marca y regularidad de la compra, entre otros.", contentFont2));
            tabulatedParagraph.add(new Phrase("\n"));
            tabulatedParagraph.setIndentationLeft(50);
            tabulatedParagraph.add(new Phrase("\n3.	AREA PRODUCCIÓN Y OPERACIONES:", contentFont3));
            tabulatedParagraph.add(new Phrase(" Mide el grado de Planeación, programación y control de las"
                    + " actividades que componen la cadena de valor y el proceso de elaboración del bien o servicio; capacidad de producción,"
                    + " capacidad instalada.  Mide la implementación y funcionamiento de los procesos internos de la empresa de cara a la obtención"
                    + " de la satisfacción de sus grupos de interés.", contentFont2));
            tabulatedParagraph.setLeading(20);
            tabulatedParagraph.setSpacingAfter(10f); // Agregar espacio después del párrafo
            tabulatedParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(tabulatedParagraph);

            document.newPage();
            document.add(new Paragraph("\n\n"));
            Paragraph tabulatedParagraph1 = new Paragraph();

            tabulatedParagraph1.setIndentationLeft(50);
            tabulatedParagraph1.add(new Phrase("\n4.	AREA FINANCIERA:", contentFont3));
            tabulatedParagraph1.add(new Phrase(" Actividades y decisiones encaminadas a maximizar el valor del negocio."
                    + "  En términos generales responde la pregunta ¿Cómo nos vemos a los ojos de los accionistas? Mide balances generales, flujo de caja,"
                    + " costos, gastos, punto equilibrio, productividad (EBITDA) las ganancias, rendimiento económico, desarrollo de la compañía y"
                    + " rentabilidad de la misma (ROE, ROI, ROA), riesgos financieros, entre otros.", contentFont2));
            tabulatedParagraph1.add(new Phrase("\n"));
            tabulatedParagraph1.setIndentationLeft(50);
            tabulatedParagraph1.add(new Phrase("\n5.	AREA TALENTO HUMANO:", contentFont3));
            tabulatedParagraph1.add(new Phrase(" Integra los procedimientos que buscan desarrollar al talento humano,"
                    + " mejorar condiciones de trabajo, velar por su adecuado reclutamiento, selección y contratación, evaluación de desempeño orientado"
                    + " a las competencias, dentro de un marco legal.  Permite responder la pregunta ¿Podemos continuar mejorando y creando valor?"
                    + " Analiza la cultura y clima organizacional para el aprendizaje y la acción del Capital Humano.", contentFont2));
            tabulatedParagraph1.add(new Phrase("\n"));
            tabulatedParagraph1.setIndentationLeft(50);
            tabulatedParagraph1.add(new Phrase("\n6.	NECESIDADES DE FORMACION O ENTRENAMIENTO (Si Aplica):", contentFont3));
            tabulatedParagraph1.add(new Phrase(" Con el fin de fortalecer las"
                    + " competencias empresariales y gerenciales del personal o de hacer parte de algunos de los programas de capacitación"
                    + ", servicio que será prestado en articulación con las Academia y el SENA.", contentFont2));
            tabulatedParagraph1.add(new Phrase("\n"));
            tabulatedParagraph1.setIndentationLeft(50);
            tabulatedParagraph1.add(new Phrase("\n7.	AREA DE ASOCIATIVIDAD (Si Aplica):", contentFont3));
            tabulatedParagraph1.add(new Phrase(" Este enfoque mide las alianzas, convenios y/o articulaciones entre la empresa y el"
                    + " sector u otras empresas que brinden competitividad manteniendo su independencia jurídica y autonomía gerencial.", contentFont2));
            tabulatedParagraph1.add(new Phrase("\n"));
            tabulatedParagraph1.setIndentationLeft(50);
            tabulatedParagraph1.add(new Phrase("\n8.	OTROS ASPECTOS A FORTALECER (Si Aplica): ", contentFont3));
            tabulatedParagraph1.add(new Phrase(" En caso de que haya necesidades muy específicas planteadas por el empresario o por el tipo de empresa,"
                    + " se puede manejar a través de este ítem siempre y cuando el asesor asignado tenga la competencia o los medios para responder a"
                    + " dicha necesidad. Ej necesidades de formular proyectos, convocatorias de proyectos especiales de desarrollo rural y empresarial,"
                    + " revisión de componentes ambientales, tecnologías de la información entre otros.", contentFont2));
            tabulatedParagraph1.setLeading(20);
            tabulatedParagraph1.setSpacingAfter(10f); // Agregar espacio después del párrafo
            tabulatedParagraph1.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(tabulatedParagraph1);

            Date currentDate2 = client.getFechaAlta();
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
            String formattedDate2 = dateFormat2.format(currentDate2);
            Date currentDate3 = client.getFechaAlta();

            // Crear un formato de fecha personalizado
            SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate3 = dateFormat3.format(currentDate3);

            document.newPage();
            document.add(new Paragraph("\n\n"));
            Paragraph paragraph4 = new Paragraph();
            paragraph4.setIndentationLeft(50);
            paragraph4.add(new Phrase("\nRAZÓN SOCIAL: " + client.getCompanyName(), contentFont2));
            paragraph4.add(new Phrase("\nREPRESENTANTE LEGAL: " + client.getName() + " " + client.getLastName(), contentFont2));
            paragraph4.add(new Phrase("\nCONTACTO EMPRESA: XXXXXXXXXXXXXXXXXXXXXXX", contentFont2));
            paragraph4.add(new Phrase("\nTIPO DE EMPRESA: " + client.getTypeOfCompany().toString().toUpperCase(), contentFont2));
            paragraph4.add(new Phrase("\nDOCUMENTO/NIT: " + client.getNIT(), contentFont2));
            paragraph4.add(new Phrase("\nFECHA DE CONSTITUCIÓN: " + formattedDate3, contentFont2));
            paragraph4.add(new Phrase("\nTELÉFONO CELULAR: XXXXXXXXXXXXXXXXXXXXXXX" + client.getPhone(), contentFont2));
            paragraph4.add(new Phrase("\nDIRECCIÓN DE LA EMPRESA: " + client.getAddress(), contentFont2));
            paragraph4.add(new Phrase("\nPÁGINA WEB : ", contentFont2));
            paragraph4.add(new Phrase("\nEMAIL: " + client.getEmail(), contentFont2));
            paragraph4.add(new Phrase("\nSECTOR ECONÓMICO: " + client.getCiiu().getTitulo(), contentFont2));
            paragraph4.add(new Phrase("\nPRODUCTO O SERVICIO: XXXXXXXXXXXXXXXXXXXXXXX", contentFont2));
            paragraph4.add(new Phrase("\n\n"));
            paragraph4.add(new Phrase("1.1	RESEÑA HISTÓRICA ", contentFont3));
            paragraph4.add(new Phrase("Esta empresa fue constituida desde " + formattedDate2 + ". Opera en unas instalaciones que"
                    + " son de su propiedad y que están ubicadas en el sector " + client.getMunicipio().getCountry() + " Barrio " + client.getMunicipio().getName() + ". Es proveedora"
                    + " de XXXXXXX XXXXXX XXXX XXXX XXXXXX XXXXXXXX XXXXXXX XXXXXXX XXXX XXXXXX XXXX XXXXX, Desarrolla sus actividades"
                    + " con " + client.getEmployeeFullTime().toString() + " empleados de planta y " + client.getEmployeePartTime().toString() + " en misión contratados a través de Desarrollo de Actividad.", contentFont2));

            paragraph4.setLeading(20);
            paragraph4.setSpacingAfter(10f); // Agregar espacio después del párrafo
            paragraph4.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(paragraph4);

            document.newPage();
            document.add(new Paragraph("\n\n"));
            Paragraph paragraph5 = new Paragraph();

            paragraph5.add(new Phrase("2. INFORME DE DIANÓSTICO ", contentFont3));
            paragraph5.setIndentationLeft(50);
            paragraph5.add(new Phrase("\n2.2. DIAGNÓSTICO ", contentFont3));
            paragraph5.add(new Phrase("\nLa información que se describe a continuación es el resultado obtenido de la información"
                    + " registrada con participación del empresario en la herramienta de diagnóstico.\n"
                    + "Se pueden incluir gráfico(S) resumen, fotografias, tablas de datos u objetos que refuercen el análisis de las áreas diagnósticadas", contentFont2));
            paragraph5.add(new Phrase("\n2.2.1 AREA LINEAMIENTOS BÁSICOS ESTRATÉGICOS ", contentFont3));
            paragraph5.add(new Phrase("\nSe realiza el análisis del área lineamientos básicos estratégicos de acuerdo"
                    + " a los principales hallazgos identificados, y con base en estos se deberá formular el plan de acción.", contentFont2));
            paragraph5.add(new Phrase("\n2.2.2 AREA DE MERCADEO Y VENTAS", contentFont3));
            paragraph5.add(new Phrase("\nSe realiza el análisis del área de mercados y ventas de acuerdo"
                    + " a los principales hallazgos identificados, y con base en estos se deberá formular el plan de acción.", contentFont2));
            paragraph5.add(new Phrase("\n2.2.3 AREA PRODUCCIÓN Y OPERACIONES", contentFont3));
            paragraph5.add(new Phrase("\nSe realiza el análisis del área de producción y operaciones  de acuerdo"
                    + " a los principales hallazgos identificados, y con base en estos se deberá formular el plan de acción.", contentFont2));
            paragraph5.add(new Phrase("\n2.2.4 AREA FINANCIERA ", contentFont3));
            paragraph5.add(new Phrase("\nSe realiza el análisis del área financiera de acuerdo"
                    + " a los principales hallazgos identificados, y con base en estos se deberá formular el plan de acción.", contentFont2));
            paragraph5.add(new Phrase("\n2.2.5 AREA TALENTO HUMANO", contentFont3));
            paragraph5.add(new Phrase("\nSe realiza el análisis del área de talento humano  de acuerdo"
                    + " a los principales hallazgos identificados, y con base en estos se deberá formular el plan de acción.", contentFont2));
            paragraph5.add(new Phrase("\n2.2.6 NECESIDADES DE FORMACION (Si Aplica)", contentFont3));
            paragraph5.add(new Phrase("\nSe realiza el análisis de las necesidades formación o entrenamiento planteadas "
                    + "por el empresario en caso de llegar a presentarse, es importante dejar claridad que en el caso de la formación"
                    + " el servicio será prestado por otra área, si es entrenamientos se plantean las temáticas de acuerdo a las fichas establecidas.", contentFont2));
            paragraph5.add(new Phrase("\n2.2.7 AREA ASOCIATIVIDAD (Si Aplica)", contentFont3));
            paragraph5.add(new Phrase("\nSe realiza el análisis del área de mercados y ventas de acuerdo"
                    + " a los principales hallazgos identificados, y con base en estos se deberá formular el plan de acción.", contentFont2));
            paragraph5.add(new Phrase("\n2.2.8 OTROS ASPECTOS A FORTALECER (Si Aplica))", contentFont3));
            paragraph5.add(new Phrase("\nSIncluir si es necesario el análisis de otras áreas o aspectos sobre los cuales se identificó"
                    + " que es relevante realizar el fortalecimiento de acuerdo a los principales hallazgos identificados y con base en estos"
                    + " se deberá formular el plan de acción. Ej Temas de licencias ambientales, implementar sistema integrados de gestión,"
                    + " proyectos especiales entre otros..", contentFont2));
            paragraph5.setLeading(20);
            paragraph5.setSpacingAfter(10f); // Agregar espacio después del párrafo
            paragraph5.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(paragraph5);

            document.newPage();
            document.add(new Paragraph("\n\n"));
            Paragraph paragraph6 = new Paragraph();
            paragraph6.add(new Phrase("3. CONCLUSIONES Y RECOMENDACIONES", contentFont3));
            paragraph6.add(new Phrase("\nEl Asesor determina según su criterio profesional los aspectos relevantes y establece la"
                    + " apertura para formular el plan de acción en las áreas que requiera mayor intervención, ", contentFont2));
            paragraph6.add(new Phrase("(focalización de las principales necesidades de la empresa).", contentFont1));
            paragraph6.add(new Phrase("\n\n"));
            paragraph6.add(new Phrase("Cordialmente,", contentFont2));
            paragraph6.add(new Phrase("\n\n"));
            paragraph6.add(new Phrase("\n\n"));
            paragraph6.add(new Phrase(client.getUser().getName().toUpperCase(), contentFont3));
            paragraph6.add(new Phrase("\nAsesor/a", contentFont2));
            paragraph6.add(new Phrase("\nCDE-SBDC", contentFont2));
            paragraph6.add(new Phrase("\n" + client.getUser().getEmail(), contentFont2));

            paragraph6.setLeading(20);
            paragraph6.setSpacingAfter(10f); // Agregar espacio después del párrafo
            paragraph6.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(paragraph6);

            // Cerrar el documento
            document.close();
            outputStream.flush();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            // Manejar errores aquí
        }
    }

    @GetMapping("/diagnostico/{id}")
    public void generarDiagnostico(HttpServletResponse response, @PathVariable Long id) {

        String[] nombregestion = new String[10];
        nombregestion[0] = "GESTIÓN ESTRATÉGICA COMERCIAL Y DE MARKETING";
        nombregestion[1] = "GESTIÓN DE PRODUCTIVIDAD Y DEL TALENTO HUMANO";
        nombregestion[2] = "GESTIÓN DE LA PRODUCTIVIDAD OPERACIONAL";
        nombregestion[3] = "GESTIÓN DE CALIDAD";
        nombregestion[4] = "GESTIÓN DE LA INNOVACIÓN";
        nombregestion[5] = "GESTIÓN FINANCIERA Y CONTABLE";
        nombregestion[6] = "GESTIÓN LOGÍSTICA";
        nombregestion[7] = "GESTIÓN DE LA TRANSFORMACIÓN DIGÍTAL";
        nombregestion[8] = "GESTIÓN DE SOSTENIBILIDAD AMBIENTAL";
        nombregestion[9] = "GESTIÓN DE LA PROPIEDAD INTELECTUAL E INDUSTRIAL";

        // Establecer el tipo de contenido de la respuesta como PDF
        response.setContentType("application/pdf");
        // Establecer el encabezado para indicar la descarga del archivo PDF
        response.setHeader("Content-Disposition", "attachment; filename=diagnosticoEmpresarial.pdf");

        try {

            // Crear una instancia del documento y del escritor
            Document document = new Document(PageSize.A4);
            OutputStream outputStream = response.getOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            // Crear una instancia de la clase PdfFooterEventHandler para manejar el pie de página
            PdfFooterEventHandler footerHandler = new PdfFooterEventHandler();
            writer.setPageEvent(footerHandler);
            // Crear una instancia de la clase PdfHeaderEventHandler para manejar el encabezado
            PdfHeaderEventHandler headerHandler = new PdfHeaderEventHandler();
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

            // Establecer el estilo de fuente para el título
            document.add(new Paragraph("\n\n"));

            Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.BOLD);
            Paragraph title = new Paragraph(client.getCompanyName().toUpperCase(), titleFont);
            title.setSpacingBefore(20f);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));
            Paragraph title1 = new Paragraph("Diagnóstico Empresarial", titleFont);

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

            //Crea tabla con 3 columnas
            PdfPTable table = new PdfPTable(3);
            table.setWidths(new float[]{5f, 75f, 20f});
            BaseColor colorHeader = new BaseColor(204, 255, 255);
            //Agrega el Titulo a la fila
            PdfPCell cell = new PdfPCell(new Paragraph("CONSOLIDADO", contentFont4));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setBackgroundColor(colorHeader);
            table.addCell(cell);
            PdfPCell cell10 = new PdfPCell(new Paragraph("CALIFICACIÓN", contentFont4));
            cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell10.setBackgroundColor(colorHeader);
            table.addCell(cell10);
            //Fila Datos
            for (int i = 0; i < 10; i++) {
                PdfPCell cell0 = new PdfPCell(new Paragraph(String.valueOf(i + 1), contentFont4));
                cell0.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell0);
                PdfPCell cell1 = new PdfPCell(new Paragraph(nombregestion[i], contentFont4));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell1);
                PdfPCell cell2 = new PdfPCell(new Paragraph(String.valueOf(processEmpresario.getDiagnosticoEmpresarial().getDiagnostico().getTotales().get(i)), contentFont4));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell2);

            }
            BaseColor colorFooter = new BaseColor(255, 255, 204);
            PdfPCell cell3 = new PdfPCell(new Paragraph("TOTAL", contentFont4));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setColspan(2);
            cell3.setBackgroundColor(colorFooter);
            table.addCell(cell3);
            PdfPCell cell4 = new PdfPCell(new Paragraph(String.valueOf(processEmpresario.getDiagnosticoEmpresarial().getDiagnostico().getTotal()), contentFont4));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setBackgroundColor(colorFooter);

            table.addCell(cell4);
            document.add(table);

            Paragraph title2 = new Paragraph("Análisis de Resultados Diagnóstico Empresarial", titleFont);
            title.setSpacingBefore(20f);
            title2.setAlignment(Element.ALIGN_CENTER);
            document.add(title2);
            document.add(new Paragraph("\n"));
            
            //Contenido de Analisis de Resultados
            Paragraph parrafo = new Paragraph("GESTIÓN ESTRATÉGICA COMERCIAL Y DE MARKETING", contentFont1);
            parrafo.setAlignment(Element.ALIGN_CENTER);
            parrafo.setSpacingBefore(20f);
            document.add(parrafo);
            Paragraph parrafo1 = new Paragraph(processEmpresario.getDiagnosticoEmpresarial().getAnalisisResultados().getGestionEstrategica(), contentFont4);
            parrafo1.setAlignment(Element.ALIGN_JUSTIFIED);
            parrafo1.setSpacingBefore(20f);
            document.add(parrafo1);
            
             Paragraph parrafo2 = new Paragraph("GESTIÓN DE PRODUCTIVIDAD Y DEL TALENTO HUMANO", contentFont1);
            parrafo2.setAlignment(Element.ALIGN_CENTER);
            parrafo2.setSpacingBefore(20f);
            document.add(parrafo2);
            Paragraph parrafo3 = new Paragraph(processEmpresario.getDiagnosticoEmpresarial().getAnalisisResultados().getGestionProductividad(), contentFont4);
            parrafo3.setAlignment(Element.ALIGN_JUSTIFIED);
            parrafo3.setSpacingBefore(20f);
            document.add(parrafo3);
            
            // Cerrar el documento
            document.close();
            outputStream.flush();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            // Manejar errores aquí
        }
    }

    private static class PdfFooterEventHandler extends PdfPageEventHelper {

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                // Obtener el número de página actual
                int pageNumber = writer.getPageNumber();

                // Obtener el contenido directo de la página
                PdfContentByte content = writer.getDirectContent();

                // Agregar imagen en el pie de página
                String imagePath = new File("PiePagina.jpg").getAbsolutePath();
                Image footerImage = Image.getInstance(imagePath);
                footerImage.scaleToFit(PageSize.A4.getWidth(), footerImage.getHeight());
                footerImage.setAbsolutePosition(0, 0);
                content.addImage(footerImage);
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }
        }
    }

    private static class PdfHeaderEventHandler extends PdfPageEventHelper {

        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            try {
                // Obtener el número de página actual
                int pageNumber = writer.getPageNumber();

                // Obtener el contenido directo de la página
                PdfContentByte content = writer.getDirectContent();

                // Agregar imagen en el encabezado
                String imagePathIzq = new File("ImagenEncabezado1.jpg").getAbsolutePath();
                Image headerImageIzq = Image.getInstance(imagePathIzq);
                headerImageIzq.scaleToFit(200, headerImageIzq.getHeight());
                float imageX1 = 36;
                float imageY1 = PageSize.A4.getHeight() - 20 - headerImageIzq.getScaledHeight();
                headerImageIzq.setAbsolutePosition(imageX1, imageY1);
                content.addImage(headerImageIzq);

                String imagePathDer = new File("ImagenEncabezado2.jpg").getAbsolutePath();
                Image headerImageDer = Image.getInstance(imagePathDer);
                headerImageDer.scaleToFit(100, 100);
                float imageX2 = PageSize.A4.getWidth() - 136;
                float imageY2 = PageSize.A4.getHeight() - 20 - headerImageDer.getScaledHeight();
                headerImageDer.setAbsolutePosition(imageX2, imageY2);
                content.addImage(headerImageDer);
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }
        }
    }
}
