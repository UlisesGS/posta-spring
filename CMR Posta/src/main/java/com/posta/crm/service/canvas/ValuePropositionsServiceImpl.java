
package com.posta.crm.service.canvas;

import com.posta.crm.entity.canvas.ValuePropositions;
import com.posta.crm.repository.canvas.ValuePropositionsRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ValuePropositionsServiceImpl implements IValuePropositionsService {

    @Autowired
    private ValuePropositionsRepository valuePropositionsRepository;

    @Override
    public ValuePropositions save(ValuePropositions valuePropositions) {
        return valuePropositionsRepository.save(valuePropositions);
    }

    @Override
    public Optional<ValuePropositions> findById(Long id) {
        return valuePropositionsRepository.findById(id);
    }

    @Override
    public ValuePropositions update(ValuePropositions valuePropositions, Long id) {
        ValuePropositions newvaluePropositions = valuePropositionsRepository.findById(id).get();
        if (newvaluePropositions != null) {
            newvaluePropositions.setProposition(valuePropositions.getProposition());
            return valuePropositionsRepository.save(newvaluePropositions);
        }
        return null;
    }

}
