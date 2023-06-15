

package com.posta.crm.entity.canvas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class CostStructure {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private List<CostComponent> variableCosts;
    private List<CostComponent> fixedCosts;
    @NotNull
    private Double totalVariableCosts;
    @NotNull
    private Double totalfixedCosts;
    @NotNull
    private Double totalCost;
   
    
}
