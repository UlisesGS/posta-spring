
package com.posta.crm.service;

import com.posta.crm.entity.Departamentos;
import com.posta.crm.entity.Municipios;
import java.util.List;


public interface IMunicipiosService {
    
    public List<Municipios>findAll();
    public List<Departamentos>findAllDeptos();
    public List<Municipios>findByDeto(Long idDepto);
}
