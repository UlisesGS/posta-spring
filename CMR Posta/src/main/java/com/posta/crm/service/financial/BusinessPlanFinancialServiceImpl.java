/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.financial;

import com.posta.crm.entity.financiero.BusinessPlanFinancial;
import com.posta.crm.entity.financiero.PresupuestoVenta;
import com.posta.crm.entity.financiero.partes.EstructuraMercado;
import com.posta.crm.repository.financial.BusinessPlanFinancialRepository;
import com.posta.crm.repository.financial.EstructuraMercadoRepository;
import com.posta.crm.repository.financial.PresupuestoVentaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class BusinessPlanFinancialServiceImpl implements IBusinessPlanFinancialService{
    
    @Autowired
    private BusinessPlanFinancialRepository businessPlanFinancialRepository;
    
    @Autowired
    private PresupuestoVentaRepository presupuestoVentaRepository;
    
    @Autowired
    private EstructuraMercadoRepository estructuraMercadoRepository;
    
    

    @Override
    public BusinessPlanFinancial save(BusinessPlanFinancial businessPlanFinancial) {
        List<EstructuraMercado>estructuraSave=businessPlanFinancial.getPresupuestoVenta().getEstructuraMercado();
        List<EstructuraMercado>estructuraUpdate = new ArrayList();
        for (EstructuraMercado estructuraMercado : estructuraSave) {
            estructuraMercado.calculos();
            estructuraUpdate.add(estructuraMercadoRepository.save(estructuraMercado));
        }
        PresupuestoVenta presupuestoVentaUpdate= businessPlanFinancial.getPresupuestoVenta();
        
        presupuestoVentaUpdate.setEstructuraMercado(estructuraUpdate);
        
        presupuestoVentaUpdate.calcular();
        businessPlanFinancial.setPresupuestoVenta(presupuestoVentaRepository.save(presupuestoVentaUpdate));
        
        return businessPlanFinancialRepository.save(businessPlanFinancial);
    }

    @Override
    public Optional<BusinessPlanFinancial> findByID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BusinessPlanFinancial update(BusinessPlanFinancial businessPlanFinancial, Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
