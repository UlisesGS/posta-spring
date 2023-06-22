
package com.posta.crm.repository;

import com.posta.crm.entity.Advisory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisoryRepository extends JpaRepository<Advisory, Long>{
    
    @Query("SELECT a FROM Advisory a WHERE a.client.id=?1")
    public Page<Advisory>findByUser(@Param("client_id") Long client_id, Pageable pageable);
}
