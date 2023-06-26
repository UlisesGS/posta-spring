/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.Channels;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IChannelsService {
    
    public Channels save(Channels channels);
    public Channels update(Channels channels, Long id);
    public Optional<Channels> findById(Long id);
    
}
