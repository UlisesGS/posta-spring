/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.CustomerSegments;
import com.posta.crm.repository.canvas.CustomerSegmentsRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerSegmentsServiceImpl implements ICustomerSegmentsService {

    @Autowired
    private CustomerSegmentsRepository customerSegmentsRepository;

    @Override
    public CustomerSegments save(CustomerSegments customerSegments) {
        return customerSegmentsRepository.save(customerSegments);
    }

    @Override
    public CustomerSegments update(CustomerSegments customerSegments, Long id) {

        CustomerSegments newcustomerSegments = customerSegmentsRepository.findById(id).get();
        if (newcustomerSegments != null) {
            newcustomerSegments.setComportanmiento(customerSegments.getComportanmiento());
            newcustomerSegments.setDemograficas(customerSegments.getDemograficas());
            newcustomerSegments.setGeograficas(customerSegments.getGeograficas());
            newcustomerSegments.setPsicograficas(customerSegments.getPsicograficas());
            return customerSegmentsRepository.save(newcustomerSegments);
        }
        return null;
    }

    @Override
    public Optional<CustomerSegments> findById(Long id) {
        return customerSegmentsRepository.findById(id);
    }

}
