/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.financial;

import com.posta.crm.entity.financiero.partes.EstructuraMercado;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IEstructuraMercadoService {
    
    public EstructuraMercado save(EstructuraMercado estructuraMercado);
    public Optional<EstructuraMercado> findByID(Long id);
    public EstructuraMercado update(EstructuraMercado businessPlanFinancial, Long id);
    
}
