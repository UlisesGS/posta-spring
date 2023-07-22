/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.CostComponent;
import com.posta.crm.entity.canvas.CostStructure;
import com.posta.crm.repository.canvas.CostComponentRepository;
import com.posta.crm.repository.canvas.CostStructureRepository;
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
public class CostStructureServiceImpl implements ICostStructureService {

    @Autowired
    private CostStructureRepository costStructureRepository;
    @Autowired
    private CostComponentRepository costComponentRepository;

    @Override
    public CostStructure save(CostStructure costStructure) {
        List<CostComponent> fijosFront = costStructure.getCostosFijos();
        List<CostComponent> variablesFront = costStructure.getCostosVariables();
        List<CostComponent> fijosFrontUpdate = new ArrayList();
        List<CostComponent> variablesFrontUpdate = new ArrayList();
        for (CostComponent costComponent : fijosFront) {
            fijosFrontUpdate.add(costComponentRepository.save(costComponent));

        }
        for (CostComponent costComponent : variablesFront) {
            variablesFrontUpdate.add(costComponentRepository.save(costComponent));
        }

        costStructure.setCostosFijos(fijosFrontUpdate);
        costStructure.setCostosVariables(variablesFrontUpdate);
        costStructure.calcularTotales();
        return costStructureRepository.save(costStructure);
    }

    @Override
    public CostStructure update(CostStructure costStructure, Long id) {
        CostStructure newCostStructure = costStructureRepository.findById(id).get();
        List<CostComponent> fijosFront = costStructure.getCostosFijos();
        List<CostComponent> variablesFront = costStructure.getCostosVariables();
        List<CostComponent> fijosFrontUpdate = new ArrayList();
        List<CostComponent> variablesFrontUpdate = new ArrayList();
        if (fijosFront != null) {
            for (CostComponent costComponent : fijosFront) {
                fijosFrontUpdate.add(costComponentRepository.save(costComponent));

            }
            newCostStructure.setCostosFijos(fijosFrontUpdate);
        }
        if (variablesFront != null) {
            for (CostComponent costComponent : variablesFront) {
                variablesFrontUpdate.add(costComponentRepository.save(costComponent));
            }
             newCostStructure.setCostosFijos(variablesFrontUpdate);
        }

        
       
        newCostStructure.calcularTotales();
        return costStructureRepository.save(newCostStructure);
    }

    @Override
    public Optional<CostStructure> findById(Long id) {
        return costStructureRepository.findById(id);
    }

    @Override
    public List<CostStructure> finAll() {
        return costStructureRepository.findAll();
    }

}
