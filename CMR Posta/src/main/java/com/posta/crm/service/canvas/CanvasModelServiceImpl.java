
package com.posta.crm.service.canvas;


import com.posta.crm.entity.canvas.CanvasModel;
import com.posta.crm.repository.canvas.CanvasModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CanvasModelServiceImpl implements ICanvasModel{
    
    @Autowired
    private CanvasModelRepository canvasModelRepository;

    @Override
    public CanvasModel save(CanvasModel canvasModel) {
        return canvasModelRepository.save(canvasModel);
    }

    @Override
    public void updata(CanvasModel canvasModel) {
        CanvasModel newCanvasModel=canvasModel;
        newCanvasModel.setCustomerSegments(canvasModel.getCustomerSegments());
        newCanvasModel.setValuePropositions(canvasModel.getValuePropositions());
        newCanvasModel.setCustomerRelationships(canvasModel.getCustomerRelationships());
        newCanvasModel.setKeyRecources(canvasModel.getKeyRecources());
        newCanvasModel.setKeyActivities(canvasModel.getKeyActivities());
        newCanvasModel.setKeyPartners(canvasModel.getKeyPartners());
        newCanvasModel.setRevenueStreams(canvasModel.getRevenueStreams());
        newCanvasModel.setCostStructure(canvasModel.getCostStructure());
        
        canvasModelRepository.save(newCanvasModel);
    }
    
}
