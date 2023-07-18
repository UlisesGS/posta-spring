/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.entity.empresario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 *
 * @author crowl
 */
@Entity
@Data
public class AnalisisResultados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 5000)
    private String gestionEstrategica;
    @Column(length = 5000)
    private String gestionProductividad;
    @Column(length = 5000)
    private String gestionOperacional;
    @Column(length = 5000)
    private String gestionCalidad;
    @Column(length = 5000)
    private String gestionInnovacion;
    @Column(length = 5000)
    private String gestionFinanciera;
    @Column(length = 5000)
    private String gestionLogistica;
    @Column(length = 5000)
    private String gestionDigital;
    @Column(length = 5000)
    private String gestionAmbiental;
    @Column(length = 5000)
    private String gestionIntelectual;
    
}
