/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.RevenueStreams;
import com.posta.crm.repository.canvas.RevenueStreamsRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class RevenueStreamsServiceImpl implements IRevenueStreamsService{

    @Autowired
    private RevenueStreamsRepository revenueStreamsRepository;
    
    @Override
    public RevenueStreams save(RevenueStreams revenueStreams) {
        return revenueStreamsRepository.save(revenueStreams);
    }

    @Override
    public RevenueStreams update(RevenueStreams revenueStreams, Long id) {
        RevenueStreams newRevenue=revenueStreamsRepository.findById(id).get();
        if(newRevenue!=null){
            newRevenue.setCanalesPago(revenueStreams.getCanalesPago());
            newRevenue.setCapitalPorpio(revenueStreams.getCapitalPorpio());
            newRevenue.setCapitalPrestamo(revenueStreams.getCapitalPrestamo());
            newRevenue.setOtros(revenueStreams.getOtros());
            return revenueStreamsRepository.save(newRevenue);
        }
        return null;
    }

    @Override
    public Optional<RevenueStreams> findById(Long id) {
        return revenueStreamsRepository.findById(id);
    }
    
}
