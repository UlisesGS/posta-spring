/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.KeyPartners;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IKeyPartnersService {
    public KeyPartners save(KeyPartners keyPartners);
    public KeyPartners update(KeyPartners keyPartners, Long id);
    public Optional<KeyPartners> findById(Long id);
    
}
