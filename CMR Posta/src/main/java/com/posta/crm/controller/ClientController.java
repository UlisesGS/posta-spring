package com.posta.crm.controller;

import com.posta.crm.entity.Businessman;
import com.posta.crm.entity.Client;
import com.posta.crm.entity.Entrepreneur;
import com.posta.crm.enums.Gender;
import com.posta.crm.service.ClientServiceImpl;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ClientServiceImpl clienteService;

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, Object> errores = new HashMap();
        result.getFieldErrors().forEach(e -> {
            errores.put(e.getField(), "el campo " + e.getField() + " " + e.getDefaultMessage());
        });
        return new ResponseEntity<>(errores, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/businessman")
    public ResponseEntity<?> saveBusinessman(@Valid @RequestBody Businessman businessman, BindingResult result) {
        if (result.hasErrors()) {
            return this.validation(result);
        }
        clienteService.save(businessman);
        return new ResponseEntity<>(businessman, HttpStatus.CREATED);
    }

    @PostMapping("/entrepreneur")
    public ResponseEntity<?> saveEntrepreneur(@Valid @RequestBody Entrepreneur entrepreneur, BindingResult result) {
        if (result.hasErrors()) {
            return this.validation(result);
        }

        return new ResponseEntity<>(clienteService.save(entrepreneur), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Client> clients = clienteService.findAll();
        if (clients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Client client = clienteService.findById(id).get();
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<?> findByGender(@PathVariable Gender gender) {
        List<Client> genders = clienteService.findByGender(gender);
        if (genders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(genders);

    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> findByType(@PathVariable String type) {

        List<Client> types = clienteService.findByType(type);
        if (type.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(types);

    }

    @GetMapping("/state/{active}")
    public ResponseEntity<?> findByState(@PathVariable Boolean active) {
        List<Client> client = clienteService.findByActive(active);
        if (client.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(client);

    }

    @PutMapping("/businessman/{id}")
    public ResponseEntity<?> updateBusinessman(@Valid @RequestBody Businessman client, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return this.validation(result);
        }

        return ResponseEntity.ok(clienteService.update(client, id));
    }

    @PutMapping("/entrepreneur/{id}")
    public ResponseEntity<?> updateBusinessman(@Valid @RequestBody Entrepreneur client, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return this.validation(result);
        }

        return ResponseEntity.ok(clienteService.update(client, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> activateDeactivate(@PathVariable Long id) {
        Optional<Client> find = clienteService.findById(id);
        if (find.isPresent()) {
            Client client = find.get();
            if (client.getActive()) {
                client.setActive(false);
                clienteService.save(client);
                return ResponseEntity.ok(client);
            } else {
                client.setActive(true);
                clienteService.save(client);
                return ResponseEntity.ok(client);
            }
        }
        return ResponseEntity.notFound().build();
    }

}
