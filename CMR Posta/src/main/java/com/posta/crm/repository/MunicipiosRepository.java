
package com.posta.crm.repository;

import com.posta.crm.entity.Municipios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipiosRepository extends JpaRepository<Municipios, Long>{
    
    @Query("SELECT a FROM Municipios a WHERE a.departamento.id=?1")
    public List<Municipios>findByDepartamento(Long idDepto);
    
}
