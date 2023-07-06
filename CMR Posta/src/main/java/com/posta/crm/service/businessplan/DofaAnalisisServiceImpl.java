/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.businessplan;

import com.posta.crm.entity.businessplan.DofaAnalisis;
import com.posta.crm.repository.businessplan.DofaAnalisisRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class DofaAnalisisServiceImpl implements IDofaAnalisisService {

    @Autowired
    private DofaAnalisisRepository dofaAnalisisRepository;

    @Override
    public DofaAnalisis save(DofaAnalisis dofaAnalisis) {
        return dofaAnalisisRepository.save(dofaAnalisis);
    }

    @Override
    public List<DofaAnalisis> findAll() {
        return dofaAnalisisRepository.findAll();
    }

    @Override
    public Optional<DofaAnalisis> findById(Long id) {
        return dofaAnalisisRepository.findById(id);
    }

    @Override
    public void update(DofaAnalisis dofaAnalisis, Long id) {
        DofaAnalisis findDofaAnalisis = dofaAnalisisRepository.findById(id).get();
        if (findDofaAnalisis != null) {
            findDofaAnalisis.setAmenazas(dofaAnalisis.getAmenazas());
            findDofaAnalisis.setDebilidades(dofaAnalisis.getDebilidades());
            findDofaAnalisis.setFotalezas(dofaAnalisis.getFotalezas());
            findDofaAnalisis.setOportunidades(dofaAnalisis.getOportunidades());
            dofaAnalisisRepository.save(findDofaAnalisis);

        }
    }

}
