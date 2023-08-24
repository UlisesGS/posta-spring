/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.controller;

import com.posta.crm.service.canvas.process.ProcessServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import com.posta.crm.entity.Process;
import java.time.Instant;
import java.util.UUID;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "*")
@RequestMapping("/image")
@RestController
public class ImageController {

    //private static final String UPLOAD_DIR = "img/";
    private static final String UPLOAD_DIR = System.getenv("UPLOAD_DIR");

    @Autowired
    private ProcessServiceImpl processServiceImpl;

    @PostMapping("/uploadCompromiso/{id}")
    public String uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        System.out.println("Hola manola");
        Process nuevoProceso = processServiceImpl.findById(id).get();

        try {
            byte[] bytes = file.getBytes();

            String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());
            Path path = Paths.get(UPLOAD_DIR + uniqueFileName);
            Files.write(path, bytes);
            nuevoProceso.setDocumentoCompromiso(path.toString());
            processServiceImpl.save(nuevoProceso);
            return "Imagen subida exitosamente!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al subir la imagen";
        }
    }

    @GetMapping(value = "/imagenCompromiso/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {
        Process nuevoProceso = processServiceImpl.findById(id).get();

        if (nuevoProceso != null) {
            String rutaImagen = nuevoProceso.getDocumentoCompromiso();
            Path path = Paths.get(rutaImagen);
            byte[] fileData = Files.readAllBytes(path);
            String contentType = detectContentType(rutaImagen);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));
            headers.setContentDisposition(ContentDisposition.builder("inline").filename(path.getFileName().toString()).build());
            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/uploadEncuesta/{id}")
    public String uploadImage1(@RequestParam("file") MultipartFile file, @PathVariable Long id) {

        Process nuevoProceso = processServiceImpl.findById(id).get();

        try {
            byte[] bytes = file.getBytes();
            String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());
            Path path = Paths.get(UPLOAD_DIR + uniqueFileName);
            Files.write(path, bytes);
            nuevoProceso.setEncuestaSatisfaccion(path.toString());
            processServiceImpl.save(nuevoProceso);
            return "Imagen subida exitosamente!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al subir la imagen";
        }
    }
//    @PostMapping("/uploadEncuesta/{id}")
//    public String uploadImage1(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
//        Process nuevoProceso = processServiceImpl.findById(id).orElse(null); // Evitar nullPointerException si no se encuentra el proceso
//
//        if (nuevoProceso == null) {
//            return "Proceso no encontrado";
//        }
//
//        try {
//            byte[] bytes = file.getBytes();
//            String originalFilename = file.getOriginalFilename();
//            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//
//            // Comprueba la extensión del archivo (puedes agregar más extensiones si es necesario)
//            if ("jpg".equalsIgnoreCase(fileExtension) || "jpeg".equalsIgnoreCase(fileExtension)
//                    || "png".equalsIgnoreCase(fileExtension) || "gif".equalsIgnoreCase(fileExtension)
//                    || "pdf".equalsIgnoreCase(fileExtension)) {
//
//                String uniqueFileName = generateUniqueFileName(originalFilename);
//                Path path = Paths.get(UPLOAD_DIR + uniqueFileName);
//                Files.write(path, bytes);
//                nuevoProceso.setImpacto(path.toString());
//                processServiceImpl.save(nuevoProceso);
//                return "Archivo subido exitosamente!";
//            } else {
//                return "Tipo de archivo no admitido. Se admiten archivos JPG, JPEG, PNG, GIF y PDF.";
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Error al subir el archivo";
//        }
//    }

    @GetMapping(value = "/imagenEncuesta/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage1(@PathVariable Long id) throws IOException {
        Process nuevoProceso = processServiceImpl.findById(id).get();

        if (nuevoProceso != null) {
            String rutaImagen = nuevoProceso.getEncuestaSatisfaccion();
            Path path = Paths.get(rutaImagen);
            byte[] fileData = Files.readAllBytes(path);
            String contentType = detectContentType(rutaImagen);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));
            headers.setContentDisposition(ContentDisposition.builder("inline").filename(path.getFileName().toString()).build());
            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/uploadCierre/{id}")
    public String uploadImage2(@RequestParam("file") MultipartFile file, @PathVariable Long id) {

        Process nuevoProceso = processServiceImpl.findById(id).get();

        try {
            byte[] bytes = file.getBytes();
            String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());
            Path path = Paths.get(UPLOAD_DIR + uniqueFileName);
            Files.write(path, bytes);
            nuevoProceso.setActaCierre(path.toString());
            processServiceImpl.save(nuevoProceso);
            return "Imagen subida exitosamente!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al subir la imagen";
        }
    }

    @GetMapping(value = "/imagenCierre/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage2(@PathVariable Long id) throws IOException {
        Process nuevoProceso = processServiceImpl.findById(id).get();

        if (nuevoProceso != null) {
            String rutaImagen = nuevoProceso.getActaCierre();
            Path path = Paths.get(rutaImagen);
            byte[] fileData = Files.readAllBytes(path);
            String contentType = detectContentType(rutaImagen);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));
            headers.setContentDisposition(ContentDisposition.builder("inline").filename(path.getFileName().toString()).build());
            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/imagenImpacto/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage3(@PathVariable Long id) throws IOException {
        Process nuevoProceso = processServiceImpl.findById(id).get();

        if (nuevoProceso != null) {
            String rutaImagen = nuevoProceso.getImpacto();
            Path path = Paths.get(rutaImagen);
            byte[] fileData = Files.readAllBytes(path);
            String contentType = detectContentType(rutaImagen);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));
            headers.setContentDisposition(ContentDisposition.builder("inline").filename(path.getFileName().toString()).build());
            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/uploadImpacto/{id}")
    public String uploadImage3(@RequestParam("file") MultipartFile file, @PathVariable Long id) {

        Process nuevoProceso = processServiceImpl.findById(id).get();

        try {
            byte[] bytes = file.getBytes();
            String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());
            Path path = Paths.get(UPLOAD_DIR + uniqueFileName);
            Files.write(path, bytes);
            nuevoProceso.setImpacto(path.toString());
            processServiceImpl.save(nuevoProceso);
            return "Imagen subida exitosamente!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al subir la imagen";
        }
    }

    //Metodo para modificar el nombre del archivo
    private String generateUniqueFileName(String originalFileName) {
        String extension = "";
        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex >= 0) {
            extension = originalFileName.substring(dotIndex);
        }
        String uniquePart = Instant.now().toEpochMilli() + "-" + UUID.randomUUID().toString();
        return uniquePart + extension;
    }

    private String detectContentType(String fileName) {
        String extension = FilenameUtils.getExtension(fileName);

        // Agrega lógica para detectar otros tipos de archivos según su extensión, por ejemplo, PDF, DOCX, etc.
        if ("pdf".equalsIgnoreCase(extension)) {
            return MediaType.APPLICATION_PDF_VALUE;
        } else if ("docx".equalsIgnoreCase(extension)) {
            return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        } else if ("jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension)) {
            return MediaType.IMAGE_JPEG_VALUE;
        } else if ("png".equalsIgnoreCase(extension)) {
            return MediaType.IMAGE_PNG_VALUE;
        } else if ("gif".equalsIgnoreCase(extension)) {
            return MediaType.IMAGE_GIF_VALUE;
        }

        // Si no se reconoce la extensión, puedes devolver un tipo de contenido genérico
        return MediaType.APPLICATION_OCTET_STREAM_VALUE;
    }

}
