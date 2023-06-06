
package com.posta.crm.repository;

import com.posta.crm.entity.Departamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentosRepository extends JpaRepository<Departamentos, Long>{
    
}
