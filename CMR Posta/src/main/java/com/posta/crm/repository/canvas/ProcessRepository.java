package com.posta.crm.repository.canvas;

import com.posta.crm.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProcessRepository extends JpaRepository<Process,Long> {
    List<Process> findTop6ByOrderByFechaAltaDesc();
}
