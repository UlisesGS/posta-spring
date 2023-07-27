/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.empresario;

import com.posta.crm.entity.ProcessEmpresario;
import com.posta.crm.entity.empresario.Diagnostico;
import com.posta.crm.entity.empresario.DiagnosticoEmpresarial;
import com.posta.crm.repository.empresario.ProcessEmpresarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class ProcessEmpresarioServiceImpl implements IProcessEmpresarioService{
    
    @Autowired
    private ProcessEmpresarioRepository processEmpresarioRepository;
    @Autowired
    private DiagnosticoEmpresarialServiceImpl diagnosticoEmpresarialServiceImpl;
    
    
    
    
    @Override
    public ProcessEmpresario save(ProcessEmpresario process) {
        ProcessEmpresario processUpdate=process;
        DiagnosticoEmpresarial diagnosticoEmpresarialUpdate=processUpdate.getDiagnosticoEmpresarial();
        if(diagnosticoEmpresarialUpdate.getId()==null){
            diagnosticoEmpresarialServiceImpl.save(diagnosticoEmpresarialUpdate);
        }
//        if(diagnosticoEmpresarialUpdate.getAnalisisEconomico().getId()==null){
//             diagnosticoEmpresarialServiceImpl.updateEconomico(diagnosticoEmpresarialUpdate,diagnosticoEmpresarialUpdate.getId());
//        }
//       if(diagnosticoEmpresarialUpdate.getAnalisisResultados().getId()==null){
//           diagnosticoEmpresarialServiceImpl.updateResultados(diagnosticoEmpresarialUpdate, diagnosticoEmpresarialUpdate.getId());
//       }
        
        processUpdate.setDiagnosticoEmpresarial(diagnosticoEmpresarialUpdate);
        return processEmpresarioRepository.save(process);
    }

    @Override
    public List<ProcessEmpresario> findAll() {
        return processEmpresarioRepository.findAll();
    }

    @Override
    public Optional<ProcessEmpresario> findById(Long id) {
        return processEmpresarioRepository.findById(id);
    }

    

    @Override
    public List<ProcessEmpresario> findAllUltimo() {
       return  processEmpresarioRepository.findTop6ByOrderByFechaAltaDesc();
    }

    @Override
    public List<ProcessEmpresario> findByNombreCliente(String termino) {
        return processEmpresarioRepository.findByNombreCliente(termino);
    }

    @Override
    public List<ProcessEmpresario> findByTypeCliente(String type) {
        return processEmpresarioRepository.findByTypeCliente(type);
    }

    @Override
    public List<ProcessEmpresario> findByTerminado(Boolean terminado) {
        return processEmpresarioRepository.findByTerminado(terminado);
    }

    @Override
    public List<ProcessEmpresario> findByEstado(String estado) {
        return processEmpresarioRepository.findByEstado(estado);
    }

    @Override
    public Page<ProcessEmpresario> paginacion(Pageable pageable) {
        return processEmpresarioRepository.findAll(pageable);
    }
    
}
