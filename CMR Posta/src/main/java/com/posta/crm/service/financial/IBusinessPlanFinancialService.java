/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.financial;

import com.posta.crm.entity.financiero.BusinessPlanFinancial;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IBusinessPlanFinancialService {
    
    public BusinessPlanFinancial save(BusinessPlanFinancial businessPlanFinancial);
    public Optional<BusinessPlanFinancial> findByID(Long id);
    public BusinessPlanFinancial updateFinancial(BusinessPlanFinancial businessPlanFinancial, Long id);
    public BusinessPlanFinancial updateCompras(BusinessPlanFinancial businessPlanFinancial, Long id);
    public BusinessPlanFinancial updateGastos(BusinessPlanFinancial businessPlanFinancial, Long id);
    public BusinessPlanFinancial updateInversion(BusinessPlanFinancial businessPlanFinancial, Long id);
    
}
