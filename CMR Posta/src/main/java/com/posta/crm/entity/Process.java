/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.posta.crm.entity.businessplan.BusinessPlan;
import com.posta.crm.entity.canvas.CanvasModel;
import com.posta.crm.entity.financiero.BusinessPlanFinancial;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author crowl
 */
@Entity
@Data
public class Process {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private SelfAssessment selfAssessment;
    @OneToOne
    private CanvasModel canvasModel;
    @OneToOne
    private BusinessPlan businessPlan;
    @OneToOne
    private BusinessPlanFinancial businessPlanFinancial;
    
    
    @ManyToOne
    private User user;
    private String estado;
    private boolean terminado;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Bogota")
    private Date fechaAlta;
    
    private String documentoCompromiso;
    private String encuestaSatisfaccion;
    private String actaCierre;

    @PrePersist
    public void prePersist(){
        this.terminado=false;
        this.fechaAlta= new Date();
    }
}
