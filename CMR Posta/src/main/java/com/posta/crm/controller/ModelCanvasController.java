/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.controller;

import com.posta.crm.entity.canvas.CanvasModel;
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
    public ResponseEntity<?> save(@Valid @RequestBody CanvasModel canvasModel, BindingResult result) {
        if (result.hasErrors()) {
            return this.validation(result);
        }
        canvasModelService.save(canvasModel);
        return new ResponseEntity<>(canvasModel, HttpStatus.CREATED);
    }
    
//    @PostMapping("/costStructure")
//    @PostMapping("/channels")
//    @PostMapping("/customerRelationShips")
//    @PostMapping("/customerSegment")
//    @PostMapping("/keyActivities")
//    @PostMapping("/keyPartnes")
//    @PostMapping("/keyRecources")
//    @PostMapping("/revenueStreams")
//    @PostMapping("/valuePropositions")
    
}
