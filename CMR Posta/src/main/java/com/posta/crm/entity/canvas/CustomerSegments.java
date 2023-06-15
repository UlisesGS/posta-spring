
package com.posta.crm.entity.canvas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class CustomerSegments {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String  demograficas;
    @NotBlank
    private String  geograficas;
    @NotBlank
    private String  psicograficas;
    @NotBlank
    private String  comportanmiento;
    
    
    
}
