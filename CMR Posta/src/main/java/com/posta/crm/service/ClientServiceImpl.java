package com.posta.crm.service;

import com.posta.crm.entity.Businessman;
import com.posta.crm.entity.Client;
import com.posta.crm.entity.Entrepreneur;
import com.posta.crm.entity.Municipio;
import com.posta.crm.enums.Gender;
import com.posta.crm.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MunicpioServiceImpl municipioService;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void activateDeactivate(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Page<Client> findByGender(Gender gender, Pageable pageable) {
        return clientRepository.findByGender(gender, pageable);
    }

    public Businessman update(Businessman client, Long id) {
        Businessman typeClient = (Businessman) clientRepository.findById(id).get();

        if (typeClient != null) {
            Businessman updateClient = typeClient;
            updateClient.setName(client.getName());
            updateClient.setLastName(client.getLastName());
            updateClient.setNIT(client.getNIT());
            updateClient.setGender(client.getGender());
            updateClient.setStudyLevel(client.getStudyLevel());
            updateClient.setEthnicGroup(client.getEthnicGroup());
            updateClient.setVictimPopulation(client.getVictimPopulation());
            updateClient.setDisability(client.getDisability());
            updateClient.setDisplacement(client.getDisplacement());
            updateClient.setPhone(client.getPhone());
            updateClient.setEmail(client.getEmail());
            updateClient.setAddress(client.getAddress());
            updateClient.setContracting(client.getContracting());
            updateClient.setFechaAlta(client.getFechaAlta());
            updateClient.setTypeOfCompany(client.getTypeOfCompany());
            updateClient.setEmployeePartTime(client.getEmployeePartTime());
            updateClient.setEmployeeFullTime(client.getEmployeeFullTime());
            updateClient.setRegistroMercantil(client.getRegistroMercantil());
            updateClient.setNumberMercantilRegistry(client.getNumberMercantilRegistry());
            updateClient.setMunicipio(client.getMunicipio());
            return clientRepository.save(updateClient);
        }
        return null;
    }

    public Entrepreneur update(Entrepreneur client, Long id) {
        Entrepreneur typeClient = (Entrepreneur) clientRepository.findById(id).get();

        if (typeClient != null) {
            Entrepreneur updateClient = (Entrepreneur) typeClient;
            updateClient.setName(client.getName());
            updateClient.setLastName(client.getLastName());
            updateClient.setNIT(client.getNIT());
            updateClient.setGender(client.getGender());
            updateClient.setStudyLevel(client.getStudyLevel());
            updateClient.setEthnicGroup(client.getEthnicGroup());
            updateClient.setVictimPopulation(client.getVictimPopulation());
            updateClient.setDisability(client.getDisability());
            updateClient.setDisplacement(client.getDisplacement());
            updateClient.setPhone(client.getPhone());
            updateClient.setEmail(client.getEmail());
            updateClient.setAddress(client.getAddress());
            updateClient.setBusinessIdea(client.getBusinessIdea());
            updateClient.setProduct(client.getProduct());
             updateClient.setMunicipio(client.getMunicipio());
            return clientRepository.save(updateClient);
        }
        return null;
    }

    @Override
    public Page<Client> paginacion(Pageable pageable) {
        return clientRepository.findAll(pageable);

    }

    @Override
    public Page<Client> byCreateTime(Pageable pageable) {
        return clientRepository.findByRegdate(pageable);
    }

    @Override
    public Page<Client> findByType(String type, Pageable pageable) {
        return clientRepository.findByType(type, pageable);
    }

    @Override
    public Page<Client> findByActive(Boolean active, Pageable pageable) {
        return clientRepository.findByActive(active, pageable);
    }


    @Override
    public Page<Client> findByMunicipio(Long idMunicipio, Pageable pageable) {
        return clientRepository.findByMunicipio(idMunicipio, pageable);
    }
    
    public List<Municipio>findByAllMunicipios(){
        return municipioService.findAll();
    }
}
