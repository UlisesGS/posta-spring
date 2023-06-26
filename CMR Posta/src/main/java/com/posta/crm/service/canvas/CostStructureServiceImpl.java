/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.CostStructure;
import com.posta.crm.repository.canvas.CostStructureRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class CostStructureServiceImpl implements ICostStructureService{

    @Autowired
    private CostStructureRepository costStructureRepository;
    
    @Override
    public CostStructure save(CostStructure costStructure) {
        return costStructureRepository.save(costStructure);
    }

    @Override
    public CostStructure update(CostStructure costStructure, Long id) {
        CostStructure newCostStructure=costStructureRepository.findById(id).get();
        if(newCostStructure!=null){
            newCostStructure.setFixedCosts(costStructure.getFixedCosts());
            newCostStructure.setVariableCosts(costStructure.getVariableCosts());
            newCostStructure.setTotalfixedCosts(costStructure.getTotalfixedCosts());
            newCostStructure.setTotalVariableCosts(costStructure.getTotalVariableCosts());
            newCostStructure.setTotalCost(costStructure.getTotalCost());
            return costStructureRepository.save(newCostStructure);
        }
        return null;
    }

    @Override
    public Optional<CostStructure> findById(Long id) {
        return costStructureRepository.findById(id);
    }
    
}
