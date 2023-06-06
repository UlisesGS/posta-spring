
package com.posta.crm.service;

import com.posta.crm.entity.Client;
import com.posta.crm.enums.Gender;
import java.util.List;
import java.util.Optional;


public interface IClientService {
    
    public List<Client> findAll();
    public Optional<Client> findById(Long id);
    public Client save(Client client);
    public void activateDeactivate(Long id);
    public List<Client> findByGender(Gender gender);
    public List<Client>findByType(String type);
    public List<Client>findByActive(Boolean active);
    public List<Client>findByMunicipio(Long idMunicipio);
}
