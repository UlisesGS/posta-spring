package com.posta.crm.entity.canvas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class CostStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<CostComponent> variableCosts;
    @OneToMany
    private List<CostComponent> fixedCosts;
    @NotNull
    private Double totalVariableCosts;
    @NotNull
    private Double totalfixedCosts;
    @NotNull
    private Double totalCost;
}
//    public void calculo() {
//        double sumVariableCosts = 0.0;
//        for (CostComponent costComponent : variableCosts) {
//            sumVariableCosts += costComponent.getAmount();
//        }
//
//// Asignar la suma a totalVariableCosts
//        totalVariableCosts = sumVariableCosts;
//
//// Sumar los valores de fixedCosts
//        double sumFixedCosts = 0.0;
//        for (CostComponent costComponent : fixedCosts) {
//            sumFixedCosts += costComponent.getAmount();
//        }
//
//// Asignar la suma a totalfixedCosts
//        totalfixedCosts = sumFixedCosts;
//
//// Calcular el totalCost sumando totalfixedCosts y totalVariableCosts
//        totalCost = totalfixedCosts + totalVariableCosts;
//    }

