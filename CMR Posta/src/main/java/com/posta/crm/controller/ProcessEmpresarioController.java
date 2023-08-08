/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posta.crm.controller;

import com.posta.crm.entity.Process;
import com.posta.crm.entity.ProcessEmpresario;
import com.posta.crm.entity.empresario.*;
import com.posta.crm.repository.empresario.*;
import com.posta.crm.service.empresario.ProcessEmpresarioServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.posta.crm.entity.Process;

/**
 *
 * @author crowl
 */
@RestController
@CrossOrigin(origins = ("*"))
@RequestMapping("/processEmpre")
public class ProcessEmpresarioController {
    
    @Autowired
    private ProcessEmpresarioServiceImpl ProcessEmpresario;
    @Autowired
    DiagnosticoRepository diagnosticoRepository;
    @Autowired
    ConceptosGeneralesRepository conceptosGeneralesRepository;
    @Autowired
    AnalisisResultadosRepository analisisResultadosRepository;
    @Autowired
    AnalisisEconomicoRepository analisisEconomicoRepository;
    @Autowired
    IndicadorRepository indicadorRepository;
    @Autowired
    PlanDeAccionRepository planDeAccionRepository;
    @Autowired
    AreaIntervenirRepository areaIntervenirRepository;
    
    //Todos los POST y PUT para cada parte del Preceso empresario
    @PostMapping
    public ResponseEntity<?>save(@RequestBody Process process){
        
        return ResponseEntity.status(HttpStatus.CREATED).body(ProcessEmpresario.save(process));
    }
    // el id que pido en este metodo es el del diagnostico
    @PutMapping("/diagnostico/{id}")
    public ResponseEntity<?>updateDiagnostico(@RequestBody Process process,@PathVariable Long id){
        Diagnostico diagnostico = new Diagnostico();
        Optional<Diagnostico> opcionDiagnostico = diagnosticoRepository.findById(id);
        ConceptosGenerales conceptosGenerales = new ConceptosGenerales();
        List<ConceptosGenerales> generalesList = process.getProcessEmpresario().getDiagnosticoEmpresarial().getDiagnostico().getConceptosGenerales();
        List<ConceptosGenerales> generalesListUpdate = new ArrayList<>();
        if (opcionDiagnostico.isPresent()){
            diagnostico=opcionDiagnostico.get();

            for (ConceptosGenerales concepto : generalesList) {

                generalesListUpdate.add(conceptosGeneralesRepository.save(concepto));
            }

            System.out.println(generalesListUpdate);
            System.out.println(diagnostico);

            diagnostico.setConceptosGenerales(generalesListUpdate);
            diagnostico.setGestionAmbiental(process.getProcessEmpresario().getDiagnosticoEmpresarial().getDiagnostico().getGestionAmbiental());
            diagnostico.setGestionDigital(process.getProcessEmpresario().getDiagnosticoEmpresarial().getDiagnostico().getGestionDigital());
            diagnostico.setGestionEstrategica(process.getProcessEmpresario().getDiagnosticoEmpresarial().getDiagnostico().getGestionEstrategica());
            diagnostico.setGestionCalidad(process.getProcessEmpresario().getDiagnosticoEmpresarial().getDiagnostico().getGestionCalidad());
            diagnostico.setGestionFinanciera(process.getProcessEmpresario().getDiagnosticoEmpresarial().getDiagnostico().getGestionFinanciera());
            diagnostico.setGestionIntelectual(process.getProcessEmpresario().getDiagnosticoEmpresarial().getDiagnostico().getGestionIntelectual());
            diagnostico.setGestionInnovacion(process.getProcessEmpresario().getDiagnosticoEmpresarial().getDiagnostico().getGestionInnovacion());
            diagnostico.setGestionOperacional(process.getProcessEmpresario().getDiagnosticoEmpresarial().getDiagnostico().getGestionOperacional());
            diagnostico.setGestionProductividad(process.getProcessEmpresario().getDiagnosticoEmpresarial().getDiagnostico().getGestionProductividad());
            diagnostico.setGestionLogistica(process.getProcessEmpresario().getDiagnosticoEmpresarial().getDiagnostico().getGestionLogistica());
            diagnostico.calcularTotales();
            return ResponseEntity.status(HttpStatus.CREATED).body(diagnosticoRepository.save(diagnostico));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/resultados/{id}")
    public ResponseEntity<?>updateResultado(@RequestBody Process front, @PathVariable Long id){
        Optional<AnalisisResultados>optional = analisisResultadosRepository.findById(id);
       AnalisisResultados analisisResultados = new AnalisisResultados();
       ProcessEmpresario processEmpresario = front.getProcessEmpresario();
        if(optional.isPresent()){
            analisisResultados = optional.get();
            analisisResultados.setGestionAmbiental(front.getProcessEmpresario().getDiagnosticoEmpresarial().getAnalisisResultados().getGestionAmbiental());
            analisisResultados.setGestionCalidad(front.getProcessEmpresario().getDiagnosticoEmpresarial().getAnalisisResultados().getGestionCalidad());
            analisisResultados.setGestionCalidad(front.getProcessEmpresario().getDiagnosticoEmpresarial().getAnalisisResultados().getGestionCalidad());
            analisisResultados.setGestionDigital(front.getProcessEmpresario().getDiagnosticoEmpresarial().getAnalisisResultados().getGestionDigital());
            analisisResultados.setGestionFinanciera(front.getProcessEmpresario().getDiagnosticoEmpresarial().getAnalisisResultados().getGestionFinanciera());
            analisisResultados.setGestionEstrategica(front.getProcessEmpresario().getDiagnosticoEmpresarial().getAnalisisResultados().getGestionEstrategica());
            analisisResultados.setGestionInnovacion(front.getProcessEmpresario().getDiagnosticoEmpresarial().getAnalisisResultados().getGestionInnovacion());
            analisisResultados.setGestionIntelectual(front.getProcessEmpresario().getDiagnosticoEmpresarial().getAnalisisResultados().getGestionIntelectual());
            analisisResultados.setGestionLogistica(front.getProcessEmpresario().getDiagnosticoEmpresarial().getAnalisisResultados().getGestionLogistica());
            analisisResultados.setGestionOperacional(front.getProcessEmpresario().getDiagnosticoEmpresarial().getAnalisisResultados().getGestionOperacional());
            System.out.println(analisisResultados);
            return ResponseEntity.status(HttpStatus.CREATED).body(analisisResultadosRepository.save(analisisResultados));
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/economico/{id}")
    public ResponseEntity<?>updateEconomico(@RequestBody Process process,@PathVariable Long id){
        AnalisisEconomico front = process.getProcessEmpresario().getDiagnosticoEmpresarial().getAnalisisEconomico();
        System.out.println("hola aguante php");
        Optional<AnalisisEconomico>optionalAnalisisEconomico = analisisEconomicoRepository.findById(id);
        AnalisisEconomico analisisEconomico = new AnalisisEconomico();
       Indicador indicador = new Indicador();
        if (optionalAnalisisEconomico.isPresent()) {

            analisisEconomico = optionalAnalisisEconomico.get();
            System.out.println(analisisEconomico);
            //para otras fuentes
            indicador = indicadorRepository.findById(analisisEconomico.getVentasMes().getId()).get();
            indicador.setMes1(front.getVentasMes().getMes1());
            indicador.setMes2(front.getVentasMes().getMes2());
            indicador.setMes3(front.getVentasMes().getMes3());
            indicador.setMes4(front.getVentasMes().getMes4());
            indicador.setMes5(front.getVentasMes().getMes5());
            indicador.setObservaciones(front.getVentasMes().getObservaciones());
            indicadorRepository.save(indicador);
            analisisEconomico.setVentasMes(indicador);


            indicador = indicadorRepository.findById(analisisEconomico.getAumentoVentas().getId()).get();
            indicador.setMes1(front.getAumentoVentas().getMes1());
            indicador.setMes2(front.getAumentoVentas().getMes2());
            indicador.setMes3(front.getAumentoVentas().getMes3());
            indicador.setMes4(front.getAumentoVentas().getMes4());
            indicador.setMes5(front.getAumentoVentas().getMes5());
            indicador.setObservaciones(front.getAumentoVentas().getObservaciones());
            indicadorRepository.save(indicador);
            analisisEconomico.setAumentoVentas(indicador);

            indicador = indicadorRepository.findById(analisisEconomico.getAccesoOtrasFuentes().getId()).get();
            indicador.setMes1(front.getAccesoOtrasFuentes().getMes1());
            indicador.setMes2(front.getAccesoOtrasFuentes().getMes2());
            indicador.setMes3(front.getAccesoOtrasFuentes().getMes3());
            indicador.setMes4(front.getAccesoOtrasFuentes().getMes4());
            indicador.setMes5(front.getAccesoOtrasFuentes().getMes5());
            indicador.setObservaciones(front.getAccesoOtrasFuentes().getObservaciones());

            indicadorRepository.save(indicador);
            analisisEconomico.setAccesoOtrasFuentes(indicador);

            indicador = indicadorRepository.findById(analisisEconomico.getEmpleosFormales().getId()).get();
            indicador.setMes1(front.getEmpleosFormales().getMes1());
            indicador.setMes2(front.getEmpleosFormales().getMes2());
            indicador.setMes3(front.getEmpleosFormales().getMes3());
            indicador.setMes4(front.getEmpleosFormales().getMes4());
            indicador.setMes5(front.getEmpleosFormales().getMes5());
            indicador.setObservaciones(front.getEmpleosFormales().getObservaciones());
            indicadorRepository.save(indicador);
            analisisEconomico.setEmpleosFormales(indicador);

            indicador = indicadorRepository.findById(analisisEconomico.getEmpleosInformales().getId()).get();
            indicador.setMes1(front.getEmpleosInformales().getMes1());
            indicador.setMes2(front.getEmpleosInformales().getMes2());
            indicador.setMes3(front.getEmpleosInformales().getMes3());
            indicador.setMes4(front.getEmpleosInformales().getMes4());
            indicador.setMes5(front.getEmpleosInformales().getMes5());
            indicador.setObservaciones(front.getEmpleosInformales().getObservaciones());
            indicadorRepository.save(indicador);
            analisisEconomico.setEmpleosInformales(indicador);

            indicador = indicadorRepository.findById(analisisEconomico.getEmpleosNuevos().getId()).get();
            indicador.setMes1(front.getEmpleosNuevos().getMes1());
            indicador.setMes2(front.getEmpleosNuevos().getMes2());
            indicador.setMes3(front.getEmpleosNuevos().getMes3());
            indicador.setMes4(front.getEmpleosNuevos().getMes4());
            indicador.setMes5(front.getEmpleosNuevos().getMes5());
            indicador.setObservaciones(front.getEmpleosNuevos().getObservaciones());
            indicadorRepository.save(indicador);
            analisisEconomico.setEmpleosNuevos(indicador);

            indicador = indicadorRepository.findById(analisisEconomico.getEmpresaExportando().getId()).get();
            indicador.setMes1(front.getEmpresaExportando().getMes1());
            indicador.setMes2(front.getEmpresaExportando().getMes2());
            indicador.setMes3(front.getEmpresaExportando().getMes3());
            indicador.setMes4(front.getEmpresaExportando().getMes4());
            indicador.setMes5(front.getEmpresaExportando().getMes5());
            indicador.setObservaciones(front.getEmpresaExportando().getObservaciones());
            indicadorRepository.save(indicador);
            analisisEconomico.setEmpresaExportando(indicador);

            indicador = indicadorRepository.findById(analisisEconomico.getVentassExportacion().getId()).get();
            indicador.setMes1(front.getVentassExportacion().getMes1());
            indicador.setMes2(front.getVentassExportacion().getMes2());
            indicador.setMes3(front.getVentassExportacion().getMes3());
            indicador.setMes4(front.getVentassExportacion().getMes4());
            indicador.setMes5(front.getVentassExportacion().getMes5());
            indicador.setObservaciones(front.getVentassExportacion().getObservaciones());
            indicadorRepository.save(indicador);
            analisisEconomico.setVentassExportacion(indicador);

            indicador = indicadorRepository.findById(analisisEconomico.getDiversificacionProductos().getId()).get();
            indicador.setMes1(front.getDiversificacionProductos().getMes1());
            indicador.setMes2(front.getDiversificacionProductos().getMes2());
            indicador.setMes3(front.getDiversificacionProductos().getMes3());
            indicador.setMes4(front.getDiversificacionProductos().getMes4());
            indicador.setMes5(front.getDiversificacionProductos().getMes5());
            indicador.setObservaciones(front.getDiversificacionProductos().getObservaciones());
            indicadorRepository.save(indicador);
            analisisEconomico.setDiversificacionProductos(indicador);

            indicador= indicadorRepository.findById(analisisEconomico.getAperturaNuevosMercados().getId()).get();
            indicador.setMes1(front.getAperturaNuevosMercados().getMes1());
            indicador.setMes2(front.getAperturaNuevosMercados().getMes2());
            indicador.setMes3(front.getAperturaNuevosMercados().getMes3());
            indicador.setMes4(front.getAperturaNuevosMercados().getMes4());
            indicador.setMes5(front.getAperturaNuevosMercados().getMes5());
            indicador.setObservaciones(front.getAperturaNuevosMercados().getObservaciones());
            indicadorRepository.save(indicador);
            analisisEconomico.setAperturaNuevosMercados(indicador);
            System.out.println("hola estoy abajo");
            System.out.println(analisisEconomico);
        return ResponseEntity.status(HttpStatus.CREATED).body(analisisEconomicoRepository.save(analisisEconomico));

        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/planAccion/{id}")
    public ResponseEntity<?>updateAccion(@RequestBody Process process, @PathVariable Long id){
        Optional<PlanDeAccion> optionalPlanDeAccion = planDeAccionRepository.findById(id);
        PlanDeAccion planDeAccion = process.getProcessEmpresario().getPlanDeAccion();
        PlanDeAccion planDeAccionUpdate = new PlanDeAccion();
        AreaIntervenir areaIntervenir = new AreaIntervenir();
        if(optionalPlanDeAccion.isPresent()){
        planDeAccionUpdate = optionalPlanDeAccion.get();
        // alineamientos basicos
        areaIntervenir= areaIntervenirRepository.findById(planDeAccion.getLineamientosBasicos().getId()).get();
        areaIntervenir.setCumplimiento(planDeAccion.getLineamientosBasicos().getCumplimiento());
        areaIntervenir.setObjetivoEstrategico(planDeAccion.getLineamientosBasicos().getObjetivoEstrategico());
        areaIntervenir.setActividadEstrategica(planDeAccion.getLineamientosBasicos().getActividadEstrategica());
        areaIntervenir.setResponsable(planDeAccion.getLineamientosBasicos().getResponsable());
        areaIntervenir.setAliadosEstrategicos(planDeAccion.getLineamientosBasicos().getAliadosEstrategicos());
        areaIntervenir.setFechaInicio(planDeAccion.getLineamientosBasicos().getFechaInicio());
        areaIntervenir.setFechaCierre(planDeAccion.getLineamientosBasicos().getFechaCierre());
        areaIntervenir.setObservaciones(planDeAccion.getLineamientosBasicos().getObservaciones());
        planDeAccionUpdate.setLineamientosBasicos(areaIntervenirRepository.save(areaIntervenir));

        // mercadeo de ventas
            areaIntervenir= areaIntervenirRepository.findById(planDeAccion.getMercadeoVentas().getId()).get();
            areaIntervenir.setCumplimiento(planDeAccion.getMercadeoVentas().getCumplimiento());
            areaIntervenir.setObjetivoEstrategico(planDeAccion.getMercadeoVentas().getObjetivoEstrategico());
            areaIntervenir.setActividadEstrategica(planDeAccion.getMercadeoVentas().getActividadEstrategica());
            areaIntervenir.setResponsable(planDeAccion.getMercadeoVentas().getResponsable());
            areaIntervenir.setAliadosEstrategicos(planDeAccion.getMercadeoVentas().getAliadosEstrategicos());
            areaIntervenir.setFechaInicio(planDeAccion.getMercadeoVentas().getFechaInicio());
            areaIntervenir.setFechaCierre(planDeAccion.getMercadeoVentas().getFechaCierre());
            areaIntervenir.setObservaciones(planDeAccion.getMercadeoVentas().getObservaciones());

            planDeAccionUpdate.setMercadeoVentas(areaIntervenirRepository.save(areaIntervenir));
        // produccion operaciones
            areaIntervenir= areaIntervenirRepository.findById(planDeAccion.getProduccionOperaciones().getId()).get();
            areaIntervenir.setCumplimiento(planDeAccion.getProduccionOperaciones().getCumplimiento());
            areaIntervenir.setObjetivoEstrategico(planDeAccion.getProduccionOperaciones().getObjetivoEstrategico());
            areaIntervenir.setActividadEstrategica(planDeAccion.getProduccionOperaciones().getActividadEstrategica());
            areaIntervenir.setResponsable(planDeAccion.getProduccionOperaciones().getResponsable());
            areaIntervenir.setAliadosEstrategicos(planDeAccion.getProduccionOperaciones().getAliadosEstrategicos());
            areaIntervenir.setFechaInicio(planDeAccion.getProduccionOperaciones().getFechaInicio());
            areaIntervenir.setFechaCierre(planDeAccion.getProduccionOperaciones().getFechaCierre());
            areaIntervenir.setObservaciones(planDeAccion.getProduccionOperaciones().getObservaciones());
            planDeAccionUpdate.setProduccionOperaciones(areaIntervenirRepository.save(areaIntervenir));
        // talento humano
            areaIntervenir= areaIntervenirRepository.findById(planDeAccion.getTalentoHumano().getId()).get();
            areaIntervenir.setCumplimiento(planDeAccion.getTalentoHumano().getCumplimiento());
            areaIntervenir.setObjetivoEstrategico(planDeAccion.getTalentoHumano().getObjetivoEstrategico());
            areaIntervenir.setActividadEstrategica(planDeAccion.getTalentoHumano().getActividadEstrategica());
            areaIntervenir.setResponsable(planDeAccion.getTalentoHumano().getResponsable());
            areaIntervenir.setAliadosEstrategicos(planDeAccion.getTalentoHumano().getAliadosEstrategicos());
            areaIntervenir.setFechaInicio(planDeAccion.getTalentoHumano().getFechaInicio());
            areaIntervenir.setFechaCierre(planDeAccion.getTalentoHumano().getFechaCierre());
            areaIntervenir.setObservaciones(planDeAccion.getTalentoHumano().getObservaciones());
            planDeAccionUpdate.setTalentoHumano(areaIntervenirRepository.save(areaIntervenir));



        return ResponseEntity.status(HttpStatus.CREATED).body(planDeAccionRepository.save(planDeAccionUpdate));
        }
       return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/ultimo")
    public ResponseEntity<?>findAllUltimo(){
        return ResponseEntity.ok().body(ProcessEmpresario.findAllUltimo());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        Optional<ProcessEmpresario> optionalProcess = ProcessEmpresario.findById(id);

        if(optionalProcess.isPresent()){
            return ResponseEntity.ok().body(optionalProcess.get());
        }
        return  ResponseEntity.notFound().build();
    }
    
    
    
}
