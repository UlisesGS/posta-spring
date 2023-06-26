/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.ValuePropositions;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IValuePropositionsService {
    
    public ValuePropositions save(ValuePropositions valuePropositions);
    public ValuePropositions update(ValuePropositions valuePropositions, Long id);
    public Optional<ValuePropositions> findById(Long id);
}
