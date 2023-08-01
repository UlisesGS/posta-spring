/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.service.empresario;

import com.posta.crm.entity.empresario.AnalisisEconomico;
import com.posta.crm.entity.empresario.AnalisisResultados;
import com.posta.crm.entity.empresario.ConceptosGenerales;
import com.posta.crm.entity.empresario.Diagnostico;
import com.posta.crm.entity.empresario.DiagnosticoEmpresarial;
import com.posta.crm.entity.empresario.Indicador;
import com.posta.crm.enums.DiagEmpr;
import com.posta.crm.repository.empresario.AnalisisEconomicoRepository;
import com.posta.crm.repository.empresario.AnalisisResultadosRepository;
import com.posta.crm.repository.empresario.ConceptosGeneralesRepository;
import com.posta.crm.repository.empresario.DiagnosticoEmpresarialRepository;
import com.posta.crm.repository.empresario.DiagnosticoRepository;
import com.posta.crm.repository.empresario.IndicadorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author crowl
 */
@Service
public class DiagnosticoEmpresarialServiceImpl implements IDiagnosticoEmpresarialService {

    @Autowired
    private DiagnosticoEmpresarialRepository diagnosticoEmpresarialRepository;
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;
    @Autowired
    private ConceptosGeneralesRepository conceptosGeneralesRepository;
    @Autowired
    private AnalisisResultadosRepository analisisResultadosRepository;
    @Autowired
    private AnalisisEconomicoRepository analisisEconomicoRepository;
    @Autowired
    private IndicadorRepository indicadorRepository;

    @Override
    public DiagnosticoEmpresarial save(DiagnosticoEmpresarial diagnosticoEmpresarial) {

        Diagnostico diagnostico = diagnosticoEmpresarial.getDiagnostico();
        List<ConceptosGenerales> conceptosGenerales = diagnostico.getConceptosGenerales();
        List<ConceptosGenerales> conceptosGeneralesUpdate = new ArrayList();

        for (ConceptosGenerales conceptosGenerale : conceptosGenerales) {
            if (!conceptosGenerales.contains(conceptosGenerale)) {
                conceptosGeneralesUpdate.add(conceptosGenerale);
            }
            conceptosGeneralesUpdate.add(conceptosGeneralesRepository.save(conceptosGenerale));
        }
        diagnostico.setConceptosGenerales(conceptosGeneralesUpdate);
        diagnosticoEmpresarial.getDiagnostico().calcularTotales();
        diagnosticoEmpresarial.setDiagnostico(diagnosticoRepository.save(diagnostico));
        if (diagnosticoEmpresarial.getId() != null) {
            return diagnosticoEmpresarial;
        }
        return diagnosticoEmpresarialRepository.save(diagnosticoEmpresarial);
    }

    @Override
    public DiagnosticoEmpresarial updateResultados(DiagnosticoEmpresarial diagnosticoEmpresarial, Long id) {
        DiagnosticoEmpresarial diagnosticoEmpresarialUpdate = diagnosticoEmpresarialRepository.findById(id).get();

        AnalisisResultados analisisResultados = diagnosticoEmpresarial.getAnalisisResultados();

        diagnosticoEmpresarialUpdate.setAnalisisResultados(analisisResultadosRepository.save(analisisResultados));

        return diagnosticoEmpresarialRepository.save(diagnosticoEmpresarialUpdate);
    }

    @Override
    public DiagnosticoEmpresarial updateEconomico(DiagnosticoEmpresarial diagnosticoEmpresarial, Long id) {
        DiagnosticoEmpresarial diagnosticoEmpresarialUpdate = diagnosticoEmpresarialRepository.findById(id).get();
        AnalisisEconomico analisisEconomico = diagnosticoEmpresarial.getAnalisisEconomico();
        AnalisisEconomico analisisEconomicoUpdate = new AnalisisEconomico();
        
        List<Indicador> ventasMes=analisisEconomico.getVentasMes();
        List<Indicador> ventasMesUpdate=new ArrayList();

        List<Indicador> aumentoVentas=analisisEconomico.getAumentoVentas();
        List<Indicador> aumentoVentasUpdate=new ArrayList();

        List<Indicador> empleosFormales=analisisEconomico.getEmpleosFormales();
        List<Indicador> empleosFormalesUpdate=new ArrayList();

        List<Indicador> empleosInformales=analisisEconomico.getEmpleosInformales();
         List<Indicador> empleosInformalesUpdate=new ArrayList();

        List<Indicador> empleosNuevos=analisisEconomico.getEmpleosNuevos();
        List<Indicador> empleosNuevosUpdate=new ArrayList();

        List<DiagEmpr> empresaExportando=analisisEconomico.getEmpresaExportando();
        List<DiagEmpr> empresaExportandoUpdate=new ArrayList();

        List<Indicador> VentasExportacion=analisisEconomico.getVentassExportacion();
        List<Indicador> VentasExportacionUpdate=new ArrayList();

        List<DiagEmpr> diversificacionProductos=analisisEconomico.getDiversificacionProductos();
        List<DiagEmpr> diversificacionProductosUpdate=new ArrayList();

        List<DiagEmpr> aperturaNuevosMercados=analisisEconomico.getAperturaNuevosMercados();
        List<DiagEmpr> aperturaNuevosMercadosUpdate=new ArrayList();

        List<DiagEmpr> accesoOtrasFuentes=analisisEconomico.getAccesoOtrasFuentes();
        List<DiagEmpr> accesoOtrasFuentesUpdate=new ArrayList();
        
        for (Indicador ventasMe : ventasMes) {
            if(!ventasMes.contains(ventasMe)){
                ventasMesUpdate.add(ventasMe);
            }
            ventasMesUpdate.add(indicadorRepository.save(ventasMe));
        }
        for (Indicador aumentoVenta : aumentoVentas) {
            if(!aumentoVentas.contains(aumentoVenta)){
                aumentoVentasUpdate.add(aumentoVenta);
            }
            aumentoVentasUpdate.add(indicadorRepository.save(aumentoVenta));
        }
        for (Indicador empleosFormale : empleosFormales) {
            if(!empleosFormales.contains(empleosFormale)){
                empleosFormalesUpdate.add(empleosFormale);
            }
            empleosFormalesUpdate.add(indicadorRepository.save(empleosFormale));
        }
        for (Indicador empleosInformale : empleosInformales) {
            if(!empleosInformales.contains(empleosInformale)){
                empleosInformalesUpdate.add(empleosInformale);
            }
            empleosInformalesUpdate.add(indicadorRepository.save(empleosInformale));
        }
        for (Indicador empleosNuevo : empleosNuevos) {
            if(!empleosNuevos.contains(empleosNuevo)){
                empleosNuevosUpdate.add(empleosNuevo);
            }
            empleosNuevosUpdate.add(indicadorRepository.save(empleosNuevo));
        }
        for (DiagEmpr diagEmpr : empresaExportando) {
                empresaExportandoUpdate.add(diagEmpr);  
        }
        for (Indicador indicador : VentasExportacion) {
            if(!VentasExportacion.add(indicador)){
                VentasExportacionUpdate.add(indicador);
            }
            VentasExportacionUpdate.add(indicadorRepository.save(indicador));
        }
         for (DiagEmpr diagEmpr : diversificacionProductos) {
                diversificacionProductosUpdate.add(diagEmpr);  
        }
        for (DiagEmpr diagEmpr : aperturaNuevosMercados) {
                aperturaNuevosMercadosUpdate.add(diagEmpr);  
        }
        for (DiagEmpr diagEmpr : accesoOtrasFuentes) {
                accesoOtrasFuentesUpdate.add(diagEmpr);  
        }
        
        analisisEconomicoUpdate.setVentasMes(ventasMesUpdate);
        analisisEconomicoUpdate.setAumentoVentas(aumentoVentasUpdate);
        analisisEconomicoUpdate.setEmpleosFormales(empleosFormalesUpdate);
        analisisEconomicoUpdate.setEmpleosInformales(empleosInformalesUpdate);
        analisisEconomicoUpdate.setEmpleosNuevos(empleosNuevosUpdate);
        analisisEconomicoUpdate.setEmpresaExportando(empresaExportandoUpdate);
        analisisEconomicoUpdate.setVentassExportacion(VentasExportacionUpdate);
        analisisEconomicoUpdate.setDiversificacionProductos(diversificacionProductosUpdate);
        analisisEconomicoUpdate.setAperturaNuevosMercados(aperturaNuevosMercadosUpdate);
        analisisEconomicoUpdate.setAccesoOtrasFuentes(accesoOtrasFuentesUpdate);
        
        diagnosticoEmpresarialUpdate.setAnalisisEconomico(analisisEconomicoRepository.save(analisisEconomicoUpdate));
        return diagnosticoEmpresarialRepository.save(diagnosticoEmpresarialUpdate);
    }

    @Override
    public Optional<DiagnosticoEmpresarial> findById(Long id) {
        return diagnosticoEmpresarialRepository.findById(id);
    }
}
