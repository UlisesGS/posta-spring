/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.entity.empresario;

import com.posta.crm.enums.DiagEmpr;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Data;

/**
 *
 * @author crowl
 */
@Entity
@Data
public class ConceptosGenerales {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(value = EnumType.STRING)
    private DiagEmpr diagEmpr;
    @Column(length = 5000)
    private String observaciones;
    
    
    
    
}
