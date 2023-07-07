/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.businessplan;

import com.posta.crm.entity.businessplan.InternalExternalAnalysis;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IInternalExternalAnalysisService {
    
    public InternalExternalAnalysis save(InternalExternalAnalysis internalExternalAnalysis);
    public List<InternalExternalAnalysis>findAll();
    public Optional<InternalExternalAnalysis> findById(Long id);
    public InternalExternalAnalysis update(InternalExternalAnalysis internalExternalAnalysis, Long id);
    
    
    
}
