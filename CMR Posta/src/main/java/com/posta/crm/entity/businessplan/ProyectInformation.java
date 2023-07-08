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
public class ProyectInformation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 1000)
    private String resumen;

   @Column(nullable = false, length = 1000)
    private String problemas;

   @Column(nullable = false, length = 1000)
    private String objetivos;

    @Column(nullable = false, length = 1000)
    private String mision;

    @Column(nullable = false, length = 1000)
    private String vision;

    @Column(nullable = false, length = 1000)
    private String valoresCorporativos;

    @Column(nullable = false, length = 1000)
    private String impactoAmbiental;

    @Column(nullable = false, length = 1000)
    private String impactoSocial;
    
}
