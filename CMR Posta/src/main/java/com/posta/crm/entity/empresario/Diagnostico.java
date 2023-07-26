/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.entity.empresario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author crowl
 */
@Entity
@Data
public class Diagnostico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany
    private List<ConceptosGenerales> conceptosGenerales;
    
    private List<Integer> gestionEstrategica;
    
    private List<Integer> gestionProductividad;
    
    private List<Integer> gestionOperacional;
    
    private List<Integer> gestionCalidad;
    
    private List<Integer> gestionInnovacion;
    
    private List<Integer> gestionFinanciera;
    
    private List<Integer> gestionLogistica;
    
    private List<Integer> gestionDigital;
    
    private List<Integer> gestionAmbiental;
    
    private List<Integer> gestionIntelectual;
    
    private List<Integer> totales;
    
    
    public void calcularTotales(){
        
        Integer totalAux=0;
        this.totales=new ArrayList();
        
            for (Integer integer : this.gestionEstrategica) {
            totalAux+=integer;
        }
            this.totales.add(totalAux);
            totalAux=0;
            for (Integer integer : this.gestionProductividad) {
                totalAux+=integer;
            }
            this.totales.add(totalAux);
            totalAux=0;
            for (Integer integer : this.gestionOperacional) {
                totalAux+=integer;
            }
            this.totales.add(totalAux);
            totalAux=0;
            for (Integer integer : this.gestionCalidad) {
                totalAux+=integer;
            }
            this.totales.add(totalAux);
            totalAux=0;
            for (Integer integer : this.gestionInnovacion) {
                totalAux+=integer;
            }
            this.totales.add(totalAux);
            totalAux=0;
            for (Integer integer : this.gestionFinanciera) {
                totalAux+=integer;
            }
            this.totales.add(totalAux);
            totalAux=0;
            for (Integer integer : this.gestionLogistica) {
                totalAux+=integer;
            }
            this.totales.add(totalAux);
            totalAux=0;
            for (Integer integer : this.gestionDigital) {
                totalAux+=integer;
            }
            this.totales.add(totalAux);
            totalAux=0;
            for (Integer integer : this.gestionAmbiental) {
                totalAux+=integer;
            }
            this.totales.add(totalAux);
            totalAux=0;
            for (Integer integer : this.gestionIntelectual) {
                totalAux+=integer;
            }
            this.totales.add(totalAux);
            totalAux=0;
        
    }
    
}
