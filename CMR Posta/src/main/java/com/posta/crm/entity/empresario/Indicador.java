/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.entity.empresario;

import com.posta.crm.enums.DiagEmpr;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Indicador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String ventasMes;
    
    private String aumentoventas;
    
    private String empleosFormales;
    
    private String empleosInformales;
    
    private String empleosNuevos;
    
    @Enumerated(value = EnumType.STRING)
    private DiagEmpr empresasExportando;
    
    private String ventasExportacion;
    
    @Enumerated(value = EnumType.STRING)
    private DiagEmpr diversificacionProductos;
    
    @Enumerated(value = EnumType.STRING)
    private DiagEmpr aperturaNuevosMercados;
    
    @Enumerated(value = EnumType.STRING)
    private DiagEmpr accesoOtrasFuentes;
    
    
}
