
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
public class Channels {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(length = 1000)
    private String informacion;
    @NotBlank
    @Column(length = 1000)
    private String evaluacion;
    @NotBlank
    @Column(length = 1000)
    private String compra;
    @NotBlank
    @Column(length = 1000)
    private String entrega;
    @NotBlank
    @Column(length = 1000)
    private String postVenta;
    
}
