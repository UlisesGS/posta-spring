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

    @Column( length = 5000)
    private String publicoObjetivo;

    @Column( length = 5000)
    private String actividadPrincipal;

    @Column( length = 5000)
    private String propuestaValor;

    @Column( length = 5000)
    private String comercializacion;

    @Column( length = 5000)
    private String operacion;

    @Column( length = 5000)
    private String equipoTrabajo;

    @Column( length = 5000)
    private String competencias;

    @Column( length = 5000)
    private String mediosDigitales;

    @Column( length = 5000)
    private String recursosNecesarios;

    @Column( length = 5000)
    private String legal;

    @Column( length = 5000)
    private String fuenteFinanciacion;
}
