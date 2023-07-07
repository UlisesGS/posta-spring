/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.businessplan;

import com.posta.crm.entity.businessplan.BusinessPlan;
import com.posta.crm.repository.businessplan.BusinessPlanRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class BusinessPlanSercviceImpl implements IBusinessPlanService{
    
    @Autowired
    private BusinessPlanRepository businessPlanRepository;

    @Override
    public BusinessPlan save(BusinessPlan businessPlan) {
        return businessPlanRepository.save(businessPlan);
    }

    @Override
    public List<BusinessPlan> findAll() {
       return businessPlanRepository.findAll();
    }

    @Override
    public Optional<BusinessPlan> findById(Long id) {
       return businessPlanRepository.findById(id);
    }

    @Override
    public BusinessPlan update(BusinessPlan businessPlan, Long id) {
        BusinessPlan newBusinessPlan=businessPlanRepository.findById(id).get();
        
        if(newBusinessPlan!=null){
            newBusinessPlan.setAnalisis(businessPlan.getAnalisis());
            newBusinessPlan.setConclusion(businessPlan.getConclusion());
            newBusinessPlan.setDofaAnalisis(businessPlan.getDofaAnalisis());
            newBusinessPlan.setImpactoAmbiental(businessPlan.getImpactoAmbiental());
            newBusinessPlan.setImpactoSocial(businessPlan.getImpactoSocial());
            newBusinessPlan.setMision(businessPlan.getMision());
            newBusinessPlan.setObjetivos(businessPlan.getObjetivos());
            newBusinessPlan.setProblemas(businessPlan.getProblemas());
            newBusinessPlan.setResumen(businessPlan.getResumen());
            newBusinessPlan.setValoresCorporativos(businessPlan.getValoresCorporativos());
            newBusinessPlan.setVision(businessPlan.getVision());
            
           return businessPlanRepository.save(newBusinessPlan);
        }
        return null;
    }
    
}
