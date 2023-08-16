package com.posta.crm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.posta.crm.entity.canvas.CanvasModel;
import com.posta.crm.enums.Contracting;
import com.posta.crm.enums.EthnicGroup;
import com.posta.crm.enums.Gender;
import com.posta.crm.enums.StudyLevel;
import com.posta.crm.enums.TypeOfCompany;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @Lob
    @Column(length = 5000)
    private String remarks;
    private Boolean active;
    private String type;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "America/Bogota")
    private Timestamp regdate;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "America/Bogota")
    private Timestamp updatedate;
    @Enumerated(value = EnumType.STRING)
    @ManyToOne
    @JoinColumn(name = "id_municipio", unique = false)
    private Municipio municipio;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    private LocalDate fechaNacimiento;
    private Integer edad;
    //Atributos Empresario
    @Enumerated(value = EnumType.STRING)
    private Contracting contracting;
    @Enumerated(value = EnumType.STRING)
    private Contracting contracting1;
    private String companyName;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Bogota")
    private Date fechaAlta;
    @Enumerated(value = EnumType.STRING)
    private TypeOfCompany typeOfCompany;
    @Column(nullable = true)
    private Integer employeePartTime;
    @Column(nullable = true)
    private Integer employeeFullTime;
    private Boolean registroMercantil;
    private String numberMercantilRegistry;
    @ManyToOne
    private Actividades ciiu;
    //Atributos Emprededor
    private String businessIdea;
    private String product;
    //Atibutos para cargar en informe de Diagnostico
    private String paginaWeb;
    private String productoServicio;
    

    
   
    
    
    
    @PrePersist
    public void active() {
        this.active = true;
    }

}
