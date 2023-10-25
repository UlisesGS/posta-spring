/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.KeyRecources;
import com.posta.crm.repository.canvas.KeyRecourcesRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class KeyRecourcesServiceImpl implements IKeyRecourcesService{

    @Autowired
    private KeyRecourcesRepository keyRecourcesRepository;
    
    @Override
    public KeyRecources save(KeyRecources keyRecources) {
        return keyRecourcesRepository.save(keyRecources);
    }

    @Override
    public KeyRecources update(KeyRecources keyRecources, Long id) {
        KeyRecources newKeyRecources=keyRecourcesRepository.findById(id).get();
        if(newKeyRecources!=null){
            
            newKeyRecources.setRecursosFisicos(keyRecources.getRecursosFisicos());
            newKeyRecources.setRecursosHumanos(keyRecources.getRecursosHumanos());
            newKeyRecources.setRecursosIntelectuales(   keyRecources.getRecursosIntelectuales());
            newKeyRecources.setRecursosTecnologicos(keyRecources.getRecursosTecnologicos());
            newKeyRecources.setOtros(keyRecources.getOtros());
            return keyRecourcesRepository.save(newKeyRecources);
        }
        
        return null;
    }

    @Override
    public Optional<KeyRecources> findById(Long id) {
        return keyRecourcesRepository.findById(id);
    }
    
}
