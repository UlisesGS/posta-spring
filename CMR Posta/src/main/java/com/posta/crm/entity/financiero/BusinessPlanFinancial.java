/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.entity.financiero;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

/**
 *
 * @author crowl
 */
@Entity
@Data
public class BusinessPlanFinancial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private PresupuestoVenta presupuestoVenta;
//    @OneToOne
//    private PresupuestoCompra presupuestoCompra;
//    @OneToOne
//    private GastoCosto gastoCosto;
//    @OneToOne
//    private PlanInversion planInversion;
    
}
