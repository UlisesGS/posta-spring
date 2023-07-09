/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.financial;

import com.posta.crm.entity.financiero.PresupuestoVenta;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IPresupuestoVentaService {
    
    public PresupuestoVenta save(PresupuestoVenta presupuestoVenta);
    public Optional<PresupuestoVenta> findByID(Long id);
    public PresupuestoVenta update(PresupuestoVenta businessPlanFinancial, Long id);
    
}
