/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.businessplan;

import com.posta.crm.entity.businessplan.BusinessPlan;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IBusinessPlanService {
    
    public BusinessPlan save(BusinessPlan businessPlan);
    public List<BusinessPlan>findAll();
    public Optional<BusinessPlan> findById(Long id);
    
}
