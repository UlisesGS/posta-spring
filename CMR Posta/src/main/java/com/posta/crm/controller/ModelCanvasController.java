/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.controller;

import com.posta.crm.entity.canvas.*;
import com.posta.crm.service.canvas.CanvasModelServiceImpl;
import com.posta.crm.service.canvas.ChannelsServiceImpl;
import com.posta.crm.service.canvas.CostStructureServiceImpl;
import com.posta.crm.service.canvas.CustomerRelationshipsServiceImpl;
import com.posta.crm.service.canvas.CustomerSegmentsServiceImpl;
import com.posta.crm.service.canvas.KeyActivitiesServiceImpl;
import com.posta.crm.service.canvas.KeyPartnersServiceImpl;
import com.posta.crm.service.canvas.KeyRecourcesServiceImpl;
import com.posta.crm.service.canvas.RevenueStreamsServiceImpl;
import com.posta.crm.service.canvas.ValuePropositionsServiceImpl;
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
@RequestMapping("/canvas")
@CrossOrigin(origins = "*")
public class ModelCanvasController {

    @Autowired
    private CanvasModelServiceImpl canvasModelService;

    @Autowired
    private CostStructureServiceImpl costStructureService;
    @Autowired
    private ChannelsServiceImpl channelsService;
    @Autowired
    private CustomerRelationshipsServiceImpl customerRelationshipsService;
    @Autowired
    private CustomerSegmentsServiceImpl customerSegmentsService;
    @Autowired
    private KeyActivitiesServiceImpl keyActivitiesService;
    @Autowired
    private KeyPartnersServiceImpl keyPartnersService;
    @Autowired
    private KeyRecourcesServiceImpl keyRecourcesService;
    @Autowired
    private RevenueStreamsServiceImpl revenueStreamsService;
    @Autowired
    private ValuePropositionsServiceImpl valuePropositionsServiceImpl;
            
            
        private ResponseEntity<?> validation(BindingResult result) {
        Map<String, Object> errores = new HashMap();
        result.getFieldErrors().forEach(e -> {
            errores.put(e.getField(), "el campo " + e.getField() + " " + e.getDefaultMessage());
        });
        return new ResponseEntity<>(errores, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        CanvasModel found = canvasModelService.findById(id).get();
        if (found != null) {
            return ResponseEntity.ok(found);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CanvasModel canvasModel) {
        
        canvasModelService.save(canvasModel);
        return new ResponseEntity<>(canvasModel, HttpStatus.CREATED);
    }
    
    @PutMapping("/model/{id}")
    public ResponseEntity<?>update(@RequestBody CanvasModel canvasModel,@PathVariable Long id){
        
        return ResponseEntity.ok(canvasModelService.update(canvasModel, id));
    }
    @PostMapping("/segmetento")
    public ResponseEntity<?>save(@RequestBody CustomerSegments customerSegments){

            return ResponseEntity.status(HttpStatus.CREATED).body(  customerSegmentsService.save(customerSegments));
    }

    @PostMapping("/propuestaValor")
    public ResponseEntity<?>save(@RequestBody ValuePropositions valuePropositions){

        return ResponseEntity.status(HttpStatus.CREATED).body(  valuePropositionsServiceImpl.save(valuePropositions));
    }
    @PostMapping("/canales")
    public ResponseEntity<?>save(@RequestBody Channels channels){

        return ResponseEntity.status(HttpStatus.CREATED).body(  channelsService.save(channels));
    }
    @PostMapping("/relaciones")
    public ResponseEntity<?>save(@RequestBody CustomerRelationships customerRelationships){

        return ResponseEntity.status(HttpStatus.CREATED).body(  customerRelationshipsService.save(customerRelationships));
    }
    @PostMapping("/recursosClaves")
    public ResponseEntity<?>save(@RequestBody KeyRecources keyRecources){

        return ResponseEntity.status(HttpStatus.CREATED).body(  keyRecourcesService.save(keyRecources));
    }
    @PostMapping("/actividadesClaves")
    public ResponseEntity<?>save(@RequestBody KeyActivities keyActivities){

        return ResponseEntity.status(HttpStatus.CREATED).body(  keyActivitiesService.save(keyActivities));
    }
    @PostMapping("/sociosClaves")
    public ResponseEntity<?>save(@RequestBody KeyPartners keyPartners){

        return ResponseEntity.status(HttpStatus.CREATED).body(  keyPartnersService.save(keyPartners));
    }
    @PostMapping("/ingresos")
    public ResponseEntity<?>save(@RequestBody RevenueStreams revenueStreams){

        return ResponseEntity.status(HttpStatus.CREATED).body(  revenueStreamsService.save(revenueStreams));
    }
    //ver como lo vamos a realizar por ahora lo dejo asi
    @PostMapping("/estructuraCostos")
    public ResponseEntity<?>save(@RequestBody CostStructure costStructure){

        return ResponseEntity.status(HttpStatus.CREATED).body(costStructureService.save(costStructure));
    }
    
}
