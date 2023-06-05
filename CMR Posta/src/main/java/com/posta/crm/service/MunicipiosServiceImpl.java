package com.posta.crm.service;

import com.posta.crm.entity.Departamentos;
import com.posta.crm.entity.Municipios;
import com.posta.crm.repository.DepartamentosRepository;
import com.posta.crm.repository.MunicipiosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MunicipiosServiceImpl implements IMunicipiosService {

    @Autowired
    private MunicipiosRepository municipioRepository;
    @Autowired
    private DepartamentosRepository departamentosRepository;

    @Override
    public List<Municipios> findAll() {
        return municipioRepository.findAll();

    }

    @Override
    public List<Departamentos> findAllDeptos() {
        return departamentosRepository.findAll();
    }

    @Override
    public List<Municipios> findByDeto(Long idDepto) {
        return municipioRepository.findByDepartamento(idDepto);
    }

}
