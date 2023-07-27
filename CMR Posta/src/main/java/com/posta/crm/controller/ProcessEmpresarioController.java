/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.controller;

import com.posta.crm.entity.ProcessEmpresario;
import com.posta.crm.service.empresario.ProcessEmpresarioServiceImpl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@CrossOrigin(origins = ("*"))
@RequestMapping("/processEmpre")
public class ProcessEmpresarioController {
    
    @Autowired
    private ProcessEmpresarioServiceImpl ProcessEmpresario;
    
    @PostMapping
    public ResponseEntity<?>save(@RequestBody ProcessEmpresario process){
        
        return ResponseEntity.status(HttpStatus.CREATED).body(ProcessEmpresario.save(process));
    }
    @PutMapping("/diagnostico")
    public ResponseEntity<?>updateDiagnostico(@RequestBody ProcessEmpresario process){
        
        return ResponseEntity.status(HttpStatus.CREATED).body(ProcessEmpresario.save(process));
    }
    
    @PutMapping("/economico")
    public ResponseEntity<?>updateEconomico(@RequestBody ProcessEmpresario process){
        
        return ResponseEntity.status(HttpStatus.CREATED).body(ProcessEmpresario.save(process));
    }
    
    @GetMapping("/ultimo")
    public ResponseEntity<?>findAllUltimo(){
        return ResponseEntity.ok().body(ProcessEmpresario.findAllUltimo());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        Optional<ProcessEmpresario> optionalProcess = ProcessEmpresario.findById(id);
        if(optionalProcess.isPresent()){
            return ResponseEntity.ok().body(optionalProcess.get());
        }
        return  ResponseEntity.notFound().build();
    }
    
    
    
}
