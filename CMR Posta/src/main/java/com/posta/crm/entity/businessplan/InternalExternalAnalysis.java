/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.entity.businessplan;

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
public class InternalExternalAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String publicoObjetivo;

    @Column(nullable = false, length = 1000)
    private String actividadPrincipal;

    @Column(nullable = false, length = 1000)
    private String propuestaValor;

    @Column(nullable = false, length = 1000)
    private String comercializacion;

    @Column(nullable = false, length = 1000)
    private String operacion;
    
    @Column(nullable = false, length = 1000)
    private String equipoTrabajo;

    @Column(nullable = false, length = 1000)
    private String competencias;

    @Column(nullable = false, length = 1000)
    private String mediosDigitales;

    @Column(nullable = false, length = 1000)
    private String recursosNecesarios;

    @Column(nullable = false, length = 1000)
    private String legal;

    @Column(nullable = false, length = 1000)
    private String fuenteFinanciacion;
}
