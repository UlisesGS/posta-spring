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
import jakarta.persistence.Lob;
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
    @Lob
    @Column(length = 5000)
    private String ventasMes;
    @Lob
    @Column(length = 5000)
    private String aumentoventas;
    @Lob
    @Column(length = 5000)
    private String empleosFormales;
    @Lob
    @Column(length = 5000)
    private String empleosInformales;
    @Lob
    @Column(length = 5000)
    private String empleosNuevos;
    @Lob
    @Column(length = 5000)
    @Enumerated(value = EnumType.STRING)
    private DiagEmpr empresasExportando;
    @Lob
    @Column(length = 5000)
    private String ventasExportacion;
    
    @Enumerated(value = EnumType.STRING)
    private DiagEmpr diversificacionProductos;
    
    @Enumerated(value = EnumType.STRING)
    private DiagEmpr aperturaNuevosMercados;
    
    @Enumerated(value = EnumType.STRING)
    private DiagEmpr accesoOtrasFuentes;
    @Lob
    @Column(length = 5000)
    private String observaciones;
    
    
}
