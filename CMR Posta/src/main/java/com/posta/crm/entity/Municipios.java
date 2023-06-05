
package com.posta.crm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "municipios")
public class Municipios {
    
    @Id
    @Column(name = "id_municipio")
    private Long id;
    @Column(name = "municipio")
    private String municipio;
    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamentos departamento;
}
