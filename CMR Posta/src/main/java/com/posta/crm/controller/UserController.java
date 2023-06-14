package com.posta.crm.controller;

import com.posta.crm.entity.Advisory;
import com.posta.crm.entity.User;
import com.posta.crm.service.AdvisoryServiceImpl;
import com.posta.crm.service.UserServiceImpl;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private AdvisoryServiceImpl advisoryService;
    

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, Object> errores = new HashMap();
        result.getFieldErrors().forEach(e -> {
            errores.put(e.getField(), "el campo " + e.getField() + " " + e.getDefaultMessage());
        });
        return new ResponseEntity<>(errores, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        User user = userService.findById(id).get();
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return this.validation(result);
        }
        User newUser = userService.save(user);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return this.validation(result);
        }
        Optional<User> find = userService.findById(id);
        if (find.isPresent()) {
            User updateUser = find.get();
            updateUser.setName(user.getName());
            updateUser.setLastName(user.getLastName());
            updateUser.setEmail(user.getEmail());
            updateUser.setPhone(user.getPhone());
            updateUser.setRole(user.getRole());
            userService.save(updateUser);
            return ResponseEntity.ok(updateUser);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>activateDeactivate(@PathVariable Long id){
        Optional<User> find = userService.findById(id);
        if(find.isPresent()){
            User user=find.get();
            if(user.getActive()){
                user.setActive(false);
                userService.save(user);
                return ResponseEntity.ok(user);
            } else {
                user.setActive(true);
                userService.save(user);
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/advisory")
    public ResponseEntity<?>saveAdvisory(@RequestBody Advisory advisory){
        Advisory newAdvisory=advisoryService.save(advisory);
        return ResponseEntity.ok(newAdvisory);
    }

    @GetMapping("/byAdvisory/{page}")
    public ResponseEntity<?>findByAdvisory(@RequestParam("user_id")Long userId, @PathVariable Integer page){
        Pageable pageable = PageRequest.of(page,10);
        Page<Advisory> advisoryes=advisoryService.findByUser(userId, pageable);
        if(advisoryes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
                return ResponseEntity.ok(advisoryes);
}

}
