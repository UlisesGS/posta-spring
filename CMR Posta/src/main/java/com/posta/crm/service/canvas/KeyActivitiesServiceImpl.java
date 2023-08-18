/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.KeyActivities;
import com.posta.crm.repository.canvas.KeyActivitiesRpository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class KeyActivitiesServiceImpl implements IKeyActivitiesService{

    @Autowired
    private KeyActivitiesRpository keyActivitiesRpository;
    
    @Override
    public KeyActivities save(KeyActivities keyActivities) {
        return keyActivitiesRpository.save(keyActivities);
    }

    @Override
    public KeyActivities update(KeyActivities keyActivities, Long id) {
        KeyActivities newkeyActivities=keyActivitiesRpository.findById(id).get();
        if(newkeyActivities!=null){
            newkeyActivities.setActividadPrincipal(keyActivities.getActividadPrincipal());
            newkeyActivities.setComunicacionMarketing(keyActivities.getComunicacionMarketing());
            newkeyActivities.setPostVenta(keyActivities.getPostVenta());
            newkeyActivities.setPrestacionServicio(keyActivities.getPrestacionServicio());
            newkeyActivities.setOtros(keyActivities.getOtros());
            return keyActivitiesRpository.save(newkeyActivities);
        }
        return null;
    }

    @Override
    public Optional<KeyActivities> findById(Long id) {
        return keyActivitiesRpository.findById(id);
    }
    
}
