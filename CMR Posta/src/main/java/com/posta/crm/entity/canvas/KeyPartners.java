
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
    
    @NotBlank
    @Column(length = 1000)
    private String proveedores;
    @NotBlank
    @Column(length = 1000)
    private String entidadesPublicas;
    @NotBlank
    @Column(length = 1000)
    private String entidadesPrivadas;
    @NotBlank
    @Column(length = 1000)
    private String academia;
    @Column(length = 1000)
    private String otros;
}
