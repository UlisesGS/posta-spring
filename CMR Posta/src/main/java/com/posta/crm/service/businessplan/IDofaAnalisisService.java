/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.businessplan;

import com.posta.crm.entity.businessplan.DofaAnalisis;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IDofaAnalisisService {
    
    public DofaAnalisis save(DofaAnalisis dofaAnalisis);
    public List<DofaAnalisis>findAll();
    public Optional<DofaAnalisis> findById(Long id);
    public DofaAnalisis update(DofaAnalisis dofaAnalisis, Long id);
}
