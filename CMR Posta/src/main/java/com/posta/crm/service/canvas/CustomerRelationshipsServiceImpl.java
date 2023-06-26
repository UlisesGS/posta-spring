/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.CustomerRelationships;
import com.posta.crm.repository.canvas.CustomerRelationshipsRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service

public class CustomerRelationshipsServiceImpl implements ICustomerRelationships {

    @Autowired
    private CustomerRelationshipsRepository customerRelationshipsRepository;
    
    @Override
    public CustomerRelationships save(CustomerRelationships customerRelationships) {
        return customerRelationshipsRepository.save(customerRelationships);
    }

    @Override
    public CustomerRelationships update(CustomerRelationships customerRelationships, Long id) {
        CustomerRelationships customer= customerRelationshipsRepository.findById(id).get();
        if(customer!=null){
            customer.setCaptacion(customerRelationships.getCaptacion());
            customer.setEstimulacion(customerRelationships.getEstimulacion());
            customer.setFidelizacion(customerRelationships.getFidelizacion());
            return customerRelationshipsRepository.save(customer);
        }
        return null;
    }

    @Override
    public Optional<CustomerRelationships> findById(Long id) {
        return customerRelationshipsRepository.findById(id);
    }
    
}
