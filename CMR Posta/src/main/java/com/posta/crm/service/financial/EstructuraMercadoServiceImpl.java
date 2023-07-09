/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.financial;

import com.posta.crm.entity.financiero.partes.EstructuraMercado;
import com.posta.crm.repository.financial.EstructuraMercadoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class EstructuraMercadoServiceImpl implements IEstructuraMercadoService{
    
    @Autowired
    private EstructuraMercadoRepository estructuraMercadoRepository;

    @Override
    public EstructuraMercado save(EstructuraMercado estructuraMercado) {
        
        estructuraMercado.calculos();
        return estructuraMercadoRepository.save(estructuraMercado);
    }

    @Override
    public Optional<EstructuraMercado> findByID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EstructuraMercado update(EstructuraMercado businessPlanFinancial, Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
