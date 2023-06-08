
package com.posta.crm.service;

import com.posta.crm.entity.Client;
import com.posta.crm.enums.Gender;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IClientService {
    
    public List<Client> findAll();
    public Optional<Client> findById(Long id);
    public Client save(Client client);
    public void activateDeactivate(Long id);
    
    public Page<Client> findByGender(Gender gender, Pageable pageable);
    
    public Page<Client> findByType(String type, Pageable pageable);
    
    public Page<Client>findByActive(Boolean active, Pageable pageable);
//    public List<Client>findByMunicipio(Long idMunicipio);
    
    public Page<Client>paginacion(Pageable pageable);
    public Page<Client>byCreateTime(Pageable pageable);
}
