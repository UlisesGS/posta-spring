/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.empresario;

import com.posta.crm.entity.Process;
import com.posta.crm.entity.ProcessEmpresario;
import com.posta.crm.entity.empresario.AreaIntervenir;
import com.posta.crm.entity.empresario.Diagnostico;
import com.posta.crm.entity.empresario.DiagnosticoEmpresarial;
import com.posta.crm.entity.empresario.PlanDeAccion;
import com.posta.crm.repository.empresario.AreaIntervenirRepository;
import com.posta.crm.repository.empresario.PlanDeAccionRepository;
import com.posta.crm.repository.empresario.ProcessEmpresarioRepository;
import java.util.ArrayList;
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
public class ProcessEmpresarioServiceImpl implements IProcessEmpresarioService {

    @Autowired
    private ProcessEmpresarioRepository processEmpresarioRepository;
    @Autowired
    private DiagnosticoEmpresarialServiceImpl diagnosticoEmpresarialServiceImpl;
    @Autowired
    private PlanDeAccionRepository planDeAccionRepository;
    @Autowired
    private AreaIntervenirRepository areaIntervenirRepository;

    @Override
    public ProcessEmpresario save(Process process) {


        ProcessEmpresario processUpdate = process.getProcessEmpresario();
        DiagnosticoEmpresarial diagnosticoEmpresarialUpdate = processUpdate.getDiagnosticoEmpresarial();
        PlanDeAccion planAccionUpdate=processUpdate.getPlanDeAccion();
        System.out.println(diagnosticoEmpresarialUpdate);
        if(diagnosticoEmpresarialUpdate.getDiagnostico().getId()==null) {
            System.out.println("diagnositco empresarial");
            processUpdate.setDiagnosticoEmpresarial(diagnosticoEmpresarialServiceImpl.save(diagnosticoEmpresarialUpdate));
        } else if (diagnosticoEmpresarialUpdate.getAnalisisResultados().getId() == null) {
            System.out.println("diagnositco resultado");
            diagnosticoEmpresarialUpdate = diagnosticoEmpresarialServiceImpl.updateResultados(diagnosticoEmpresarialUpdate, diagnosticoEmpresarialUpdate.getId());
        } else if (diagnosticoEmpresarialUpdate.getAnalisisEconomico().getId() == null && diagnosticoEmpresarialUpdate.getAnalisisResultados().getId() != null) {
            System.out.println("diagnositco economico");
            diagnosticoEmpresarialUpdate = diagnosticoEmpresarialServiceImpl.updateEconomico(diagnosticoEmpresarialUpdate, diagnosticoEmpresarialUpdate.getId());
        } else if (planAccionUpdate != null) {
            //PlanDeAccion planDeAccion = new PlanDeAccion();
            areaIntervenirRepository.save(planAccionUpdate.getLineamientosBasicos());
            areaIntervenirRepository.save(planAccionUpdate.getMercadeoVentas());
            areaIntervenirRepository.save(planAccionUpdate.getProduccionOperaciones());
            areaIntervenirRepository.save(planAccionUpdate.getTalentoHumano());
            planDeAccionRepository.save(planAccionUpdate);
        }

        processUpdate.setDiagnosticoEmpresarial(diagnosticoEmpresarialUpdate);
        processUpdate.setPlanDeAccion(planAccionUpdate);
        return processEmpresarioRepository.save(processUpdate);
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
        return processEmpresarioRepository.findTop6ByOrderByFechaAltaDesc();
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
