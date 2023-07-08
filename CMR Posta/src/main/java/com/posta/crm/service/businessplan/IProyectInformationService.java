/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.businessplan;

import com.posta.crm.entity.businessplan.ProyectInformation;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IProyectInformationService {
    
    public ProyectInformation save(ProyectInformation proyectInformation);
    public Optional<ProyectInformation>findById(Long id);
    public ProyectInformation update(ProyectInformation proyectInformation, Long id);
    
}
