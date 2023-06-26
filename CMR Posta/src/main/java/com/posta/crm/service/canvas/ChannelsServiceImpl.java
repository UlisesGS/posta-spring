/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.Channels;
import com.posta.crm.repository.canvas.ChannelsRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class ChannelsServiceImpl implements IChannelsService{

    @Autowired
    private ChannelsRepository channelsRepository;
    
    @Override
    public Channels save(Channels channels) {
        return channelsRepository.save(channels);
    }

    @Override
    public Channels update(Channels channels, Long id) {
        Channels newChannel=channelsRepository.findById(id).get();
        if(newChannel!=null){
            newChannel.setCompra(channels.getCompra());
            newChannel.setEntrega(channels.getEntrega());
            newChannel.setEvaluacion(channels.getEvaluacion());
            newChannel.setInformacion(channels.getInformacion());
            newChannel.setPostVenta(channels.getPostVenta());
            return channelsRepository.save(newChannel);
        }
        return null;
    }

    @Override
    public Optional<Channels> findById(Long id) {
        return channelsRepository.findById(id);
    }
    
}
