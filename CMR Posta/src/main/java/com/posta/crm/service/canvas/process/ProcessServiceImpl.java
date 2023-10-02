package com.posta.crm.service.canvas.process;

import com.posta.crm.entity.Process;
import com.posta.crm.repository.canvas.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProcessServiceImpl implements IProcessService{
    @Autowired
    private ProcessRepository processRepository;
    @Override
    public List<Process> findAll() {
        return processRepository.findAll();
    }

    @Override
    public Optional<Process> findById(Long id) {
        return processRepository.findById(id);
    }

    @Override
    public Process save(Process process) {
        return processRepository.save(process);
    }

    @Override
    public List<Process> findAllUltimo() {
        return processRepository.findTop6ByOrderByFechaAltaDesc();
    }

    @Override
    public List<Process> findByNombreCliente(String termino) {
        return processRepository.findByNombreCliente(termino);
    }

    @Override
    public List<Process> findByTypeCliente(String type) {
        if(type.equals("entrepreneur")){
            return processRepository.findByTypeCliente(type);
        }
return processRepository.findByTypeClienteE(type);

    }

    @Override
    public List<Process> findByTerminado(Boolean terminado) {
        return processRepository.findByTerminado(terminado);
    }

    @Override
    public List<Process> findByEstado(String estado) {
        return processRepository.findByEstado(estado);
    }
    @Override
    public Page<Process> paginacion(Pageable pageable) {
        return processRepository.findAll(pageable);
    }

    @Override
    public Page<Process> buscarPorUsuario(Long id, Pageable pageable) {
        return processRepository.buscarPorUsuario(id,pageable);
    }
}
