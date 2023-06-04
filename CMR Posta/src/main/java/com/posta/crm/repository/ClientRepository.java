
package com.posta.crm.repository;

import com.posta.crm.entity.Client;
import com.posta.crm.enums.Gender;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    
    @Query("SELECT a FROM Client a WHERE a.gender=:gender")
    public List<Client> findByGender(@Param("gender") Gender gender);
    public List<Client> findByType(String type);
    public List<Client>findByActive(Boolean active);
    
    
}
