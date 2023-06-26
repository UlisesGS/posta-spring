/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.KeyActivities;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IKeyActivitiesService {
    
    public KeyActivities save(KeyActivities keyActivities);
    public KeyActivities update(KeyActivities keyActivities, Long id);
    public Optional<KeyActivities> findById(Long id);
    
}
