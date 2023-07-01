package com.posta.crm.service.canvas.process;

import com.posta.crm.entity.Process;
import com.posta.crm.repository.canvas.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
