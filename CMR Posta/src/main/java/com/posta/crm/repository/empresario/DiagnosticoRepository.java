/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.repository.empresario;

import com.posta.crm.entity.empresario.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author crowl
 */
@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long>{
    
}
