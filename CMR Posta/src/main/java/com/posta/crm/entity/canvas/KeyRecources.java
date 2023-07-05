
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
public class KeyRecources {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(length = 1000)
    private String recursosHumanos;
    @NotBlank
    @Column(length = 1000)
    private String recursosFisicos;
    @NotBlank
    @Column(length = 1000)
    private String recursosIntelectuales;
    @NotBlank
    @Column(length = 1000)
    private String recursosTecnologicos;
    
}
