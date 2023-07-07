/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.businessplan;

import com.posta.crm.entity.businessplan.InternalExternalAnalysis;
import com.posta.crm.repository.businessplan.InternalExternalAnalysisRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class InternalExternalAnalysisServiceImpl implements IInternalExternalAnalysisService{
    
    @Autowired
    private InternalExternalAnalysisRepository internalExternalAnalysisRepository;

    @Override
    public InternalExternalAnalysis save(InternalExternalAnalysis internalExternalAnalysis) {
        return internalExternalAnalysisRepository.save(internalExternalAnalysis);
    }

    @Override
    public List<InternalExternalAnalysis> findAll() {
        return internalExternalAnalysisRepository.findAll();
    }

    @Override
    public Optional<InternalExternalAnalysis> findById(Long id) {
        return internalExternalAnalysisRepository.findById(id);
    }

    @Override
    public InternalExternalAnalysis update(InternalExternalAnalysis internalExternalAnalysis, Long id) {
        
        InternalExternalAnalysis findInternal=internalExternalAnalysisRepository.findById(id).get();
        
        if(findInternal!=null){
            
            findInternal.setActividadPrincipal(internalExternalAnalysis.getActividadPrincipal());
            findInternal.setComercializacion(internalExternalAnalysis.getComercializacion());
            findInternal.setCompetencias(internalExternalAnalysis.getCompetencias());
            findInternal.setEquipoTrabajo(internalExternalAnalysis.getEquipoTrabajo());
            findInternal.setFuenteFinanciacion(internalExternalAnalysis.getFuenteFinanciacion());
            findInternal.setLegal(internalExternalAnalysis.getLegal());
            findInternal.setMediosDigitales(internalExternalAnalysis.getMediosDigitales());
            findInternal.setOperacion(internalExternalAnalysis.getOperacion());
            findInternal.setPropuestaValor(internalExternalAnalysis.getPropuestaValor());
            findInternal.setPublicoObjetivo(internalExternalAnalysis.getPublicoObjetivo());
            findInternal.setRecursosNecesarios(internalExternalAnalysis.getRecursosNecesarios());
            
            return internalExternalAnalysisRepository.save(findInternal);
        }
        return null;
    }
    
}
