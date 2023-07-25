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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;

@CrossOrigin(origins = "*")
@RestController
public class ImageController {
//    @Value("${S3_BUCKET_URL}")
//    private String S3BucketUrl;
    
    //private static final String UPLOAD_DIR = "img/";
    private static final String UPLOAD_DIR = System.getenv("UPLOAD_DIR");

    @Autowired
    private ProcessServiceImpl processServiceImpl;

    @PostMapping("/uploadCompromiso/{id}")
    public String uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Long id) {

        Process nuevoProceso = processServiceImpl.findById(id).get();
        System.out.println(UPLOAD_DIR);
        try {
            byte[] bytes = file.getBytes();
            String fileName = file.getOriginalFilename();
            String fullPath = UPLOAD_DIR + fileName;
           nuevoProceso.setDocumentoCompromiso(fullPath);
            processServiceImpl.save(nuevoProceso);
            return "Imagen subida exitosamente!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al subir la imagen";
        }
    }
    
    
    @GetMapping(value = "/imagenCompromiso/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable Long id) throws IOException {
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
            String fileName = file.getOriginalFilename();
            String fullPath = UPLOAD_DIR + fileName;
           nuevoProceso.setEncuestaSatisfaccion(fullPath);
            processServiceImpl.save(nuevoProceso);
            return "Imagen subida exitosamente!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al subir la imagen";
        }
    }
    
    
    @GetMapping(value = "/imagenEncuesta/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage1(@PathVariable Long id) throws IOException {
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
            String fileName = file.getOriginalFilename();
            String fullPath = UPLOAD_DIR + fileName;
           nuevoProceso.setActaCierre(fullPath);
            processServiceImpl.save(nuevoProceso);
            return "Imagen subida exitosamente!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al subir la imagen";
        }
    }
    
    
    @GetMapping(value = "/imagenCierre/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage2(@PathVariable Long id) throws IOException {
        Process nuevoProceso = processServiceImpl.findById(id).get();
        
        
        if (nuevoProceso != null) {
            String rutaImagen = nuevoProceso.getActaCierre();
            Path path = Paths.get(rutaImagen);
            return Files.readAllBytes(path);
        }
        return null;
    }
}
