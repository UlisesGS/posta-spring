/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.CostStructure;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface ICostStructureService {
     public CostStructure save(CostStructure costStructure);
    public CostStructure update(CostStructure costStructure, Long id);
    public Optional<CostStructure> findById(Long id);
    
}
