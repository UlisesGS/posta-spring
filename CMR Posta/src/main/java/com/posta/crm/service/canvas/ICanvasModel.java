
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.CanvasModel;
import java.util.Optional;


public interface ICanvasModel {
    
    public CanvasModel save(CanvasModel canvasModel);
    public CanvasModel updata(CanvasModel canvasModel, Long id);
    public Optional<CanvasModel> findById(Long id);
}
