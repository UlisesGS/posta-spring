/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.RevenueStreams;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IRevenueStreamsService {
    public RevenueStreams save(RevenueStreams revenueStreams);
    public RevenueStreams update(RevenueStreams revenueStreams, Long id);
    public Optional<RevenueStreams> findById(Long id);
}
