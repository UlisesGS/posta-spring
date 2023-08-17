/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.KeyPartners;
import com.posta.crm.repository.canvas.KeyPartnersRepository;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class KeyPartnersServiceImpl implements IKeyPartnersService{

    @Autowired
    private KeyPartnersRepository keyPartnersRepository;
    
    @Override
    public KeyPartners save(KeyPartners keyPartners) {
        return keyPartnersRepository.save(keyPartners);
    }

    @Override
    public KeyPartners update(KeyPartners keyPartners, Long id) {
        KeyPartners newPartners=keyPartnersRepository.findById(id).get();
        if(newPartners!=null){
            newPartners.setAcademia(keyPartners.getAcademia());
            newPartners.setEntidadesPrivadas(keyPartners.getEntidadesPrivadas());
            newPartners.setEntidadesPublicas(keyPartners.getEntidadesPublicas());
            newPartners.setProveedores(keyPartners.getProveedores());
            newPartners.setOtros(keyPartners.getOtros());
            return keyPartnersRepository.save(newPartners);
        }
        return null;
    }

    @Override
    public Optional<KeyPartners> findById(Long id) {
        return keyPartnersRepository.findById(id);
    }
    
}
