/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.empresario;

import com.posta.crm.entity.empresario.AnalisisEconomico;
import com.posta.crm.entity.empresario.AnalisisResultados;
import com.posta.crm.entity.empresario.ConceptosGenerales;
import com.posta.crm.entity.empresario.Diagnostico;
import com.posta.crm.entity.empresario.DiagnosticoEmpresarial;
import com.posta.crm.entity.empresario.Indicador;
import com.posta.crm.repository.empresario.AnalisisEconomicoRepository;
import com.posta.crm.repository.empresario.AnalisisResultadosRepository;
import com.posta.crm.repository.empresario.ConceptosGeneralesRepository;
import com.posta.crm.repository.empresario.DiagnosticoEmpresarialRepository;
import com.posta.crm.repository.empresario.DiagnosticoRepository;
import com.posta.crm.repository.empresario.IndicadorRepository;
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
public class DiagnosticoEmpresarialServiceImpl implements IDiagnosticoEmpresarialService{
    
    @Autowired
    private DiagnosticoEmpresarialRepository diagnosticoEmpresarialRepository;
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;
    @Autowired
    private ConceptosGeneralesRepository conceptosGeneralesRepository;
    @Autowired
    private AnalisisResultadosRepository analisisResultadosRepository;
    @Autowired
    private AnalisisEconomicoRepository analisisEconomicoRepository;
    @Autowired
    private IndicadorRepository indicadorRepository;
    
    

    @Override
    public DiagnosticoEmpresarial save(DiagnosticoEmpresarial diagnosticoEmpresarial) {
        Diagnostico diagnostico=diagnosticoEmpresarial.getDiagnostico();
        List<ConceptosGenerales> conceptosGenerales=diagnostico.getConceptosGenerales();
        List<ConceptosGenerales> conceptosGeneralesUpdate=new ArrayList();
        
        for (ConceptosGenerales conceptosGenerale : conceptosGenerales) {
            conceptosGeneralesUpdate.add(conceptosGeneralesRepository.save(conceptosGenerale));
        }
        diagnostico.setConceptosGenerales(conceptosGeneralesUpdate);
        diagnosticoEmpresarial.getDiagnostico().calcularTotales();
        diagnosticoEmpresarial.setDiagnostico(diagnosticoRepository.save(diagnostico));
        
        return diagnosticoEmpresarialRepository.save(diagnosticoEmpresarial);
    }

    @Override
    public Optional<DiagnosticoEmpresarial> findById(Long id) {
        return diagnosticoEmpresarialRepository.findById(id);
    }

    @Override
    public DiagnosticoEmpresarial updateResultados(DiagnosticoEmpresarial diagnosticoEmpresarial, Long id) {
        DiagnosticoEmpresarial diagnosticoEmpresarialUpdate=diagnosticoEmpresarialRepository.findById(id).get();
        
        AnalisisResultados analisisResultados=diagnosticoEmpresarial.getAnalisisResultados();
        
        diagnosticoEmpresarialUpdate.setAnalisisResultados(analisisResultadosRepository.save(analisisResultados));
        
        return diagnosticoEmpresarialRepository.save(diagnosticoEmpresarialUpdate);
    }

    @Override
    public DiagnosticoEmpresarial updateEconomico(DiagnosticoEmpresarial diagnosticoEmpresarial, Long id) {
        DiagnosticoEmpresarial diagnosticoEmpresarialUpdate=diagnosticoEmpresarialRepository.findById(id).get();
        AnalisisEconomico analisisEconomico=diagnosticoEmpresarial.getAnalisisEconomico();
        List<Indicador>indicador=diagnosticoEmpresarial.getAnalisisEconomico().getIndicadores();
        
        AnalisisEconomico analisisEconomicoUpdate=new AnalisisEconomico();
        
        List<Indicador>indicadorUpdate=new ArrayList();
        
        for (Indicador indicador1 : indicador) {
            indicadorUpdate.add(indicadorRepository.save(indicador1));
        }
        for (Indicador indicador1 : indicadorUpdate) {
            
        }
        analisisEconomicoUpdate.setIndicadores(indicador);
        analisisEconomicoUpdate.setObservaciones(analisisEconomico.getObservaciones());
        
        diagnosticoEmpresarialUpdate.setAnalisisEconomico(analisisEconomicoRepository.save(analisisEconomicoUpdate));

                                
        return diagnosticoEmpresarialRepository.save(diagnosticoEmpresarialUpdate);
    }
    
}
