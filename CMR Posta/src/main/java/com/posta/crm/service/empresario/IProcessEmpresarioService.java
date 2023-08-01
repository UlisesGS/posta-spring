/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.empresario;

import com.posta.crm.entity.Process;
import com.posta.crm.entity.ProcessEmpresario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author crowl
 */
public interface IProcessEmpresarioService {
    
    public List<ProcessEmpresario> findAll();
    public Optional<ProcessEmpresario> findById(Long id);
    public ProcessEmpresario save(Process process);
    public List<ProcessEmpresario>findAllUltimo();
    List<ProcessEmpresario>findByNombreCliente(String termino);
    List<ProcessEmpresario>findByTypeCliente(String type);
    List<ProcessEmpresario>findByTerminado(Boolean terminado);
    List<ProcessEmpresario>findByEstado(String estado);
    public Page<ProcessEmpresario> paginacion(Pageable pageable);
    
}
