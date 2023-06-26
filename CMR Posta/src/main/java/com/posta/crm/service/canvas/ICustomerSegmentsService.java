/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.CustomerSegments;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface ICustomerSegmentsService {
    
    public CustomerSegments save(CustomerSegments customerSegments);
    public CustomerSegments update(CustomerSegments customerSegments, Long id);
    public Optional<CustomerSegments> findById(Long id);
    
}
