/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.controller;

import com.posta.crm.entity.empresario.DiagnosticoEmpresarial;
import com.posta.crm.service.empresario.DiagnosticoEmpresarialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author crowl
 */
@RestController
@RequestMapping("/diagEmp")
@CrossOrigin(origins= "*")
public class DiagnosticoEmpresarialController {
    
    @Autowired
    private DiagnosticoEmpresarialServiceImpl diagnosticoEmpresarialServiceImpl;
    
    //PostMapping
    @PostMapping
    public ResponseEntity<?>guardar(@RequestBody DiagnosticoEmpresarial diagnosticoEmpresarial){
        
        diagnosticoEmpresarialServiceImpl.save(diagnosticoEmpresarial);
        return new ResponseEntity<>(diagnosticoEmpresarial, HttpStatus.CREATED);
    }
    
    //Todos los PutMapping
    @PutMapping("/resultados/{id}")
    public ResponseEntity<?>guardarAnalisisResultados(@RequestBody DiagnosticoEmpresarial diagnosticoEmpresarial, @PathVariable Long id){
        
        return new ResponseEntity<>(diagnosticoEmpresarialServiceImpl.updateResultados(diagnosticoEmpresarial, id), HttpStatus.CREATED);
    }
           
    @PutMapping("/economicos/{id}")
    public ResponseEntity<?>guardarAnalisisEconomicos(@RequestBody DiagnosticoEmpresarial diagnosticoEmpresarial, @PathVariable Long id){
        
        return new ResponseEntity<>(diagnosticoEmpresarialServiceImpl.updateEconomico(diagnosticoEmpresarial, id), HttpStatus.CREATED);
    }
            
             
}
