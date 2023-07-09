/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.financial;

import com.posta.crm.entity.financiero.PresupuestoVenta;
import com.posta.crm.repository.financial.PresupuestoVentaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class PresupuestoVentaServiceImpl implements IPresupuestoVentaService{
    
    @Autowired
    private PresupuestoVentaRepository presupuestoVentaRepository ;

    @Override
    public PresupuestoVenta save(PresupuestoVenta presupuestoVenta) {
        return presupuestoVentaRepository.save(presupuestoVenta);
    }

    @Override
    public Optional<PresupuestoVenta> findByID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PresupuestoVenta update(PresupuestoVenta businessPlanFinancial, Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
