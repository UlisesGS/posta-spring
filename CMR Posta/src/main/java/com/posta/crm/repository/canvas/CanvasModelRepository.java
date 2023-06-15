
package com.posta.crm.repository.canvas;

import com.posta.crm.entity.canvas.CanvasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanvasModelRepository  extends JpaRepository<CanvasModel, Long>{
    
}
