package com.posta.crm.service.canvas.process;

import com.posta.crm.entity.Process;

import java.util.List;
import java.util.Optional;

public interface IProcessService {
    public List<Process> findAll();
    public Optional<Process> findById(Long id);
    public Process save(Process process);
    public List<Process>findAllUltimo();
    List<Process>findByNombreCliente(String termino);
    List<Process>findByTypeCliente(String type);
    List<Process>findByTerminado(Boolean terminado);
    List<Process>findByEstado(String estado);
}
