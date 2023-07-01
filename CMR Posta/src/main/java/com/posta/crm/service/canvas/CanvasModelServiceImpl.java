
package com.posta.crm.service.canvas;


import com.posta.crm.entity.canvas.CanvasModel;
import com.posta.crm.repository.canvas.CanvasModelRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CanvasModelServiceImpl implements ICanvasModel{
    
    @Autowired
    private CanvasModelRepository canvasModelRepository;

    @Override
    public CanvasModel save(CanvasModel canvasModel) {
        System.out.println(canvasModel);
        return canvasModelRepository.save(canvasModel);
    }

    @Override
    public CanvasModel update(CanvasModel canvasModel, Long id) {
        
        CanvasModel newCanvasModel=canvasModelRepository.findById(id).get();
        
        if(newCanvasModel!=null){
        newCanvasModel.setCustomerSegments(canvasModel.getCustomerSegments());
        newCanvasModel.setValuePropositions(canvasModel.getValuePropositions());
        newCanvasModel.setChannels(canvasModel.getChannels());
        newCanvasModel.setCustomerRelationships(canvasModel.getCustomerRelationships());
        newCanvasModel.setKeyRecources(canvasModel.getKeyRecources());
        newCanvasModel.setKeyActivities(canvasModel.getKeyActivities());
        newCanvasModel.setKeyPartners(canvasModel.getKeyPartners());
        newCanvasModel.setRevenueStreams(canvasModel.getRevenueStreams());
        newCanvasModel.setCostStructure(canvasModel.getCostStructure());
        return canvasModelRepository.save(newCanvasModel);
        }
       return null;
    }

    @Override
    public Optional<CanvasModel> findById(Long id) {
        return canvasModelRepository.findById(id);
    }
    
}
