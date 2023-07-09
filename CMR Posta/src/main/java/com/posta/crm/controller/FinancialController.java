/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.controller;

import com.posta.crm.entity.financiero.BusinessPlanFinancial;
import com.posta.crm.service.financial.BusinessPlanFinancialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author crowl
 */
@RestController
@RequestMapping("/financial")
@CrossOrigin(origins= "*")
public class FinancialController {
    
    
    @Autowired
    private BusinessPlanFinancialServiceImpl businessPlanFinancialServiceImpl;
    
    //Metodos POST
    @PostMapping()
    public ResponseEntity<?>save(@RequestBody BusinessPlanFinancial businessPlanFinancial){
        
        
        return new ResponseEntity<>(businessPlanFinancialServiceImpl.save(businessPlanFinancial), HttpStatus.CREATED);
    }
    
}
