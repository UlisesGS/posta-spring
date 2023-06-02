
package com.posta.crm.service;

import com.posta.crm.entity.User;
import java.util.List;
import java.util.Optional;


public interface IUserService {
    
    public List<User> findAll();
    public Optional<User> findById(Long id);
    public User save(User user);
    public void activateDeactivate(Long id);
    
}
