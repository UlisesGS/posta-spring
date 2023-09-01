
package com.posta.crm.entity.canvas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class KeyPartners {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @Column(length = 2500)
    private String proveedores;
    
    @Column(length = 2500)
    private String entidadesPublicas;
    
    @Column(length = 2500)
    private String entidadesPrivadas;
    
    @Column(length = 2500)
    private String academia;
    @Column(length = 2500)
    private String otros;
}
