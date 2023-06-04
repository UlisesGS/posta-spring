
package com.posta.crm.entity;

import com.posta.crm.enums.Contracting;
import com.posta.crm.enums.EthnicGroup;
import com.posta.crm.enums.Gender;
import com.posta.crm.enums.StudyLevel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotNull
    @Column(unique = true)
    private Long NIT;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private StudyLevel studyLevel;
    @Enumerated(value = EnumType.STRING)
    private EthnicGroup ethnicGroup;
    @NotNull
    private Boolean victimPopulation;
    @NotNull
    private Boolean disability;
    @NotNull
    private Boolean displacement;
    @NotBlank
    @Column(unique = true)
    private String phone;
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    private String address;
    private String remarks;
    private Boolean active;
    private String type;
    
    @PrePersist
    public void active(){
        this.active=true;
    }
   
    
    //Tipo de cliente hacer extend
    
}
