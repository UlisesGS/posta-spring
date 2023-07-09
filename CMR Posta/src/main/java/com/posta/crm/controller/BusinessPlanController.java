/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.controller;

import com.posta.crm.entity.businessplan.BusinessPlan;
import com.posta.crm.entity.businessplan.DofaAnalisis;
import com.posta.crm.entity.businessplan.InternalExternalAnalysis;
import com.posta.crm.entity.businessplan.ProyectInformation;
import com.posta.crm.service.businessplan.BusinessPlanSercviceImpl;
import com.posta.crm.service.businessplan.DofaAnalisisServiceImpl;
import com.posta.crm.service.businessplan.InternalExternalAnalysisServiceImpl;
import com.posta.crm.service.businessplan.ProyectInformationServiceImpl;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/plan")
@CrossOrigin(origins = "*")
public class BusinessPlanController {

    @Autowired
    private DofaAnalisisServiceImpl dofaAnalisisService;
    @Autowired
    private BusinessPlanSercviceImpl businessPlanSercvice;
    @Autowired
    private InternalExternalAnalysisServiceImpl internalExternalAnalysisService;
    @Autowired
    private ProyectInformationServiceImpl proyectInformationServiceImpl;
    

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, Object> errores = new HashMap();
        result.getFieldErrors().forEach(e -> {
            errores.put(e.getField(), "el campo " + e.getField() + " " + e.getDefaultMessage());
        });
        return new ResponseEntity<>(errores, HttpStatus.NOT_FOUND);
    }

    //Metodos POST
    @PostMapping("/save")
    public ResponseEntity<?> guardarPlan(@Valid @RequestBody BusinessPlan businessPlan, BindingResult result) {
        if (result.hasErrors()) {
            return this.validation(result);
        }
        businessPlanSercvice.save(businessPlan);
        return new ResponseEntity<>(businessPlan, HttpStatus.CREATED);
    }
    
    @PostMapping("/analisis")
    public ResponseEntity<?>guardarAnalisis(@RequestBody InternalExternalAnalysis internalExternalAnalysis ){
        return ResponseEntity.status(HttpStatus.CREATED).body(internalExternalAnalysisService.save(internalExternalAnalysis));
    }

    @PostMapping("/dofa")
    public ResponseEntity<?>guardarAnalisis(@RequestBody DofaAnalisis dofaAnalisis ){
        return ResponseEntity.status(HttpStatus.CREATED).body(dofaAnalisisService.save(dofaAnalisis));
    }
    
    @PostMapping("/proyect")
    public ResponseEntity<?>guardarProyecto(@RequestBody ProyectInformation proyectInformation ){
        return ResponseEntity.status(HttpStatus.CREATED).body(proyectInformationServiceImpl.save(proyectInformation));
    }
    
    
    //Metodos GET
    @GetMapping("/listar")
    public ResponseEntity<?> listarTodo() {
        List<BusinessPlan> findAll = businessPlanSercvice.findAll();
        if (findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(findAll);
    }

    @GetMapping("/buscarId/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id) {
        BusinessPlan findPlan = businessPlanSercvice.findById(id).get();
        if (findPlan != null) {
            return ResponseEntity.ok(findPlan);
        }
        return ResponseEntity.notFound().build();
    }
    
    //Metodos PUT
    
    @PutMapping("/businessPut")
    public ResponseEntity<?>busineesPut(@RequestBody BusinessPlan businessPlan, @PathVariable Long id){
        
        return ResponseEntity.ok().body(businessPlanSercvice.update(businessPlan, id));
    }
    
    @PutMapping("/analisisPut")
    public ResponseEntity<?>analisisPut(@RequestBody InternalExternalAnalysis analisis, @PathVariable Long id){
        return ResponseEntity.ok().body(internalExternalAnalysisService.update(analisis, id));
    }
    
    @PutMapping("/dofaPut")
    public ResponseEntity<?>dofaPut(@RequestBody DofaAnalisis dofaAnalisis, @PathVariable Long id){
        return ResponseEntity.ok().body(dofaAnalisisService.update(dofaAnalisis, id));
    }
    
     @PutMapping("/informacionPut")
    public ResponseEntity<?>dofaPut(@RequestBody ProyectInformation proyectInformation, @PathVariable Long id){
        return ResponseEntity.ok().body(proyectInformationServiceImpl.update(proyectInformation, id));
    }
}
