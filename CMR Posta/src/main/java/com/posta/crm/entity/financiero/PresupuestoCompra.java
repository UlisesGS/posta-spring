/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.entity.financiero;


import com.posta.crm.entity.financiero.partes.EstructuraCompras;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.Data;

/**
 *
 * @author crowl
 */
@Entity
@Data
public class PresupuestoCompra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombreProcucto;
    private String tipoProducto;
    private Double cantidadProducto;
    
    @OneToMany
    private List<EstructuraCompras>estructuraCompras;
    
    private Double total=0.0;
    
    private Double totalAnual=0.0;
   
    public void sacarTotales(){
        
        for (EstructuraCompras estructuraCompra : estructuraCompras) {
            this.total+=estructuraCompra.getTotalUnitario();
        }
        this.totalAnual=(this.total*this.cantidadProducto);
    }
    
}
