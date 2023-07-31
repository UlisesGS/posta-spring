/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.posta.crm.service.empresario;

import com.posta.crm.entity.empresario.DiagnosticoEmpresarial;
import java.util.Optional;

/**
 *
 * @author crowl
 */
public interface IDiagnosticoEmpresarialService {
    
    public DiagnosticoEmpresarial save(DiagnosticoEmpresarial diagnosticoEmpresarial);
    public Optional<DiagnosticoEmpresarial>findById(Long id);

    public DiagnosticoEmpresarial updateResultados(DiagnosticoEmpresarial diagnosticoEmpresarial, Long id);
    public DiagnosticoEmpresarial updateEconomico(DiagnosticoEmpresarial diagnosticoEmpresarial, Long id);
}
