
package com.posta.crm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "departamentos")
public class Departamentos {
    
    @Id
    @Column(name = "id_departamento")
    private Long id;
    @Column(name = "departamento")
    private String departamento;
    
    
}
