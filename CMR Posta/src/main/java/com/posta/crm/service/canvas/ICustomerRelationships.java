/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.CustomerRelationships;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface ICustomerRelationships {
    public CustomerRelationships save(CustomerRelationships customerRelationships);
    public CustomerRelationships update(CustomerRelationships customerRelationships, Long id);
    public Optional<CustomerRelationships> findById(Long id);
}
