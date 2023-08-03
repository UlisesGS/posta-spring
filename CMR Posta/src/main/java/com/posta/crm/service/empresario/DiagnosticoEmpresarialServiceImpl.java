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

        System.out.println(diagnosticoEmpresarial.getId());
        DiagnosticoEmpresarial diagnosticoEmpresarial1 = diagnosticoEmpresarialRepository.findById(diagnosticoEmpresarial.getId()).get();
        Diagnostico diagnostico = diagnosticoEmpresarial1.getDiagnostico();
        List<ConceptosGenerales> conceptosGenerales = diagnostico.getConceptosGenerales();
        List<ConceptosGenerales> conceptosGeneralesUpdate = new ArrayList();

        List<Integer> estrategica=diagnostico.getGestionEstrategica();
        List<Integer> productividad=diagnostico.getGestionProductividad();
        List<Integer> operacional=diagnostico.getGestionOperacional();

        for (ConceptosGenerales conceptosGenerale : conceptosGenerales) {
            if (!conceptosGenerales.contains(conceptosGenerale)) {
                conceptosGeneralesUpdate.add(conceptosGenerale);
            }
            conceptosGeneralesUpdate.add(conceptosGeneralesRepository.save(conceptosGenerale));
        }

        for (Integer estrategicas : diagnostico.getGestionEstrategica()) {
            if (!estrategica.contains(estrategicas)) {
                estrategica.add(estrategicas);
            }
            diagnostico.setGestionEstrategica(estrategica);
        }

        diagnostico.setConceptosGenerales(conceptosGeneralesUpdate);
        diagnosticoEmpresarial1.getDiagnostico().calcularTotales();

        if (diagnosticoEmpresarial1.getId() != null) {

            diagnosticoEmpresarial1.setDiagnostico(diagnosticoRepository.save(diagnostico));
            return diagnosticoEmpresarial1;
        }

        diagnosticoEmpresarial1.setDiagnostico(diagnosticoRepository.save(diagnostico));
        return diagnosticoEmpresarialRepository.save(diagnosticoEmpresarial1);
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
        System.out.println(diagnosticoEmpresarial);
        DiagnosticoEmpresarial diagnosticoEmpresarialUpdate = diagnosticoEmpresarialRepository.findById(id).get();
        AnalisisEconomico analisisEconomico = diagnosticoEmpresarial.getAnalisisEconomico();
        AnalisisEconomico analisisEconomicoUpdate = new AnalisisEconomico();
        Indicador indicador = new Indicador();
        // analisisEconomicoUpdate.setVentasMes(analisisEconomico.getVentasMes());
        indicador = indicadorRepository.save(analisisEconomico.getVentasMes());
        analisisEconomicoUpdate.setVentasMes(indicador);

        indicador = indicadorRepository.save(analisisEconomico.getAumentoVentas());
        analisisEconomicoUpdate.setAumentoVentas(indicador);
        indicador = indicadorRepository.save(analisisEconomico.getEmpleosInformales());
        analisisEconomicoUpdate.setEmpleosInformales(indicador);

        indicador = indicadorRepository.save(analisisEconomico.getEmpleosFormales());
        analisisEconomicoUpdate.setEmpleosFormales(indicador);

        indicador = indicadorRepository.save(analisisEconomico.getEmpleosNuevos());
        analisisEconomicoUpdate.setEmpleosNuevos(indicador);
        indicador = indicadorRepository.save(analisisEconomico.getEmpresaExportando());
        analisisEconomicoUpdate.setEmpresaExportando(indicador);

        indicador = indicadorRepository.save(analisisEconomico.getVentassExportacion());
        analisisEconomicoUpdate.setVentassExportacion(indicador);

        indicador = indicadorRepository.save(analisisEconomico.getDiversificacionProductos());
        analisisEconomicoUpdate.setDiversificacionProductos(indicador);

        indicador = indicadorRepository.save(analisisEconomico.getAperturaNuevosMercados());
        analisisEconomicoUpdate.setAperturaNuevosMercados(indicador);

       indicador=indicadorRepository.save(analisisEconomico.getAccesoOtrasFuentes());
        analisisEconomicoUpdate.setAccesoOtrasFuentes(indicador);

        diagnosticoEmpresarialUpdate.setAnalisisEconomico(analisisEconomicoRepository.save(analisisEconomicoUpdate));
        return diagnosticoEmpresarialRepository.save(diagnosticoEmpresarialUpdate);
    }

    @Override
    public Optional<DiagnosticoEmpresarial> findById(Long id) {
        return diagnosticoEmpresarialRepository.findById(id);
    }
}
