package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.CostComponent;
import com.posta.crm.repository.canvas.CostComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CostComponentImpl implements ICostComponentService{
    @Autowired
    private CostComponentRepository costComponentRepository;
    @Override
    public List<CostComponent> findAll() {
        return costComponentRepository.findAll();
    }
}
