/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.entity.empresario;

import com.posta.crm.enums.DiagEmpr;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;

/**
 *
 * @author crowl
 */
@Entity
@Data
public class AnalisisEconomico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany
    private List<Indicador> ventasMes;
    @OneToMany
    private List<Indicador> aumentoVentas;
    @OneToMany
    private List<Indicador> empleosFormales;
    @OneToMany
    private List<Indicador> empleosInformales;
    @OneToMany
    private List<Indicador> empleosNuevos;
    
    @Enumerated(value = EnumType.STRING)
    private List<DiagEmpr> empresaExportando;
    @OneToMany
    private List<Indicador> ventassExportacion;
   @Enumerated(value = EnumType.STRING)
    private List<DiagEmpr> diversificacionProductos;
    @Enumerated(value = EnumType.STRING)
    private List<DiagEmpr> aperturaNuevosMercados;
    @Enumerated(value = EnumType.STRING)
    private List<DiagEmpr> accesoOtrasFuentes;
}
