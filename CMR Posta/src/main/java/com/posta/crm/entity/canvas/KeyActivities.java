
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
public class KeyActivities {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(length = 1000)
    private String actividadPrincipal;
    @NotBlank
    @Column(length = 1000)
    private String prestacionServicio;
    @NotBlank
    @Column(length = 1000)
    private String comunicacionMarketing;
    @NotBlank
    @Column(length = 1000)
    private String postVenta;
    
}
