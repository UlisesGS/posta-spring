/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.businessplan;

import com.posta.crm.entity.businessplan.ProyectInformation;
import com.posta.crm.repository.businessplan.ProyectInformationRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class ProyectInformationServiceImpl implements IProyectInformationService{
    
    @Autowired
    private ProyectInformationRepository proyectInformationRepository;

    @Override
    public ProyectInformation save(ProyectInformation proyectInformation) {
        return proyectInformationRepository.save(proyectInformation);
    }

    @Override
    public Optional<ProyectInformation> findById(Long id) {
        return proyectInformationRepository.findById(id);
    }

    @Override
    public ProyectInformation update(ProyectInformation proyectInformation, Long id) {
        ProyectInformation newProyectInformation=proyectInformationRepository.findById(id).get();
        
        if(newProyectInformation!=null){
            newProyectInformation.setImpactoAmbiental(proyectInformation.getImpactoAmbiental());
            newProyectInformation.setImpactoSocial(proyectInformation.getImpactoSocial());
            newProyectInformation.setMision(proyectInformation.getMision());
            newProyectInformation.setObjetivos(proyectInformation.getObjetivos());
            newProyectInformation.setProblemas(proyectInformation.getProblemas());
            newProyectInformation.setResumen(proyectInformation.getResumen());
            newProyectInformation.setValoresCorporativos(proyectInformation.getValoresCorporativos());
            newProyectInformation.setVision(proyectInformation.getVision());
            return proyectInformationRepository.save(newProyectInformation);
            
        }
        
        return null;
    }
    
}
