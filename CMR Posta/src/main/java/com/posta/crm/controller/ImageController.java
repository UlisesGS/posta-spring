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
import org.springframework.http.MediaType;


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

        Process nuevoProceso = processServiceImpl.findById(id).get();

        try {
            byte[] bytes = file.getBytes();
            
            String uniqueFileName=generateUniqueFileName(file.getOriginalFilename());
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
    public @ResponseBody
    byte[] getImage(@PathVariable Long id) throws IOException {
        Process nuevoProceso = processServiceImpl.findById(id).get();

        if (nuevoProceso != null) {
            String rutaImagen = nuevoProceso.getDocumentoCompromiso();
            Path path = Paths.get(rutaImagen);
            return Files.readAllBytes(path);
        }
        return null;
    }


    @PostMapping("/uploadEncuesta/{id}")
    public String uploadImage1(@RequestParam("file") MultipartFile file, @PathVariable Long id) {

        Process nuevoProceso = processServiceImpl.findById(id).get();

        try {
            byte[] bytes = file.getBytes();
            String uniqueFileName=generateUniqueFileName(file.getOriginalFilename());
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

    @GetMapping(value = "/imagenEncuesta/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage1(@PathVariable Long id) throws IOException {
        Process nuevoProceso = processServiceImpl.findById(id).get();

        if (nuevoProceso != null) {
            String rutaImagen = nuevoProceso.getEncuestaSatisfaccion();
            Path path = Paths.get(rutaImagen);
            return Files.readAllBytes(path);
        }
        return null;
    }

    @PostMapping("/uploadCierre/{id}")
    public String uploadImage2(@RequestParam("file") MultipartFile file, @PathVariable Long id) {

        Process nuevoProceso = processServiceImpl.findById(id).get();

        try {
            byte[] bytes = file.getBytes();
           String uniqueFileName=generateUniqueFileName(file.getOriginalFilename());
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
    public @ResponseBody
    byte[] getImage2(@PathVariable Long id) throws IOException {
        Process nuevoProceso = processServiceImpl.findById(id).get();

        if (nuevoProceso != null) {
            String rutaImagen = nuevoProceso.getActaCierre();
            Path path = Paths.get(rutaImagen);
            return Files.readAllBytes(path);
        }
        return null;
    }
    
    @GetMapping(value = "/imagenImpacto/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage3(@PathVariable Long id) throws IOException {
        Process nuevoProceso = processServiceImpl.findById(id).get();

        if (nuevoProceso != null) {
            String rutaImagen = nuevoProceso.getEncuestaSatisfaccion();
            Path path = Paths.get(rutaImagen);
            return Files.readAllBytes(path);
        }
        return null;
    }

    @PostMapping("/uploadImpacto/{id}")
    public String uploadImage3(@RequestParam("file") MultipartFile file, @PathVariable Long id) {

        Process nuevoProceso = processServiceImpl.findById(id).get();

        try {
            byte[] bytes = file.getBytes();
           String uniqueFileName=generateUniqueFileName(file.getOriginalFilename());
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
}
