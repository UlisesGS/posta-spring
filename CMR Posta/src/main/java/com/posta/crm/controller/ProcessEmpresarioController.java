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
        List<ConceptosGenerales> generalesList = new ArrayList<>();
        if (opcionDiagnostico.isPresent()){
           // ver el tema de conceptos generales

                process.getProcessEmpresario().getDiagnosticoEmpresarial().getDiagnostico().getConceptosGenerales().forEach(front->{

                    generalesList.add(conceptosGeneralesRepository.save(front));

                });


            diagnostico.setConceptosGenerales(generalesList);
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

            return ResponseEntity.status(HttpStatus.CREATED).body(analisisResultadosRepository.save(analisisResultados));
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/economico/{id}")
    public ResponseEntity<?>updateEconomico(@RequestBody Process process,@PathVariable Long id){
        Optional<AnalisisEconomico>optionalAnalisisEconomico = analisisEconomicoRepository.findById(id);
        AnalisisEconomico analisisEconomico = new AnalisisEconomico();
       Indicador indicador = new Indicador();
        if (optionalAnalisisEconomico.isPresent()) {
            analisisEconomico = optionalAnalisisEconomico.get();
            //para otras fuentes
            indicador = indicadorRepository.findById(analisisEconomico.getVentasMes().getId()).get();
            indicadorRepository.save(indicador);
            analisisEconomico.setVentasMes(indicador);


            indicador = indicadorRepository.findById(analisisEconomico.getAumentoVentas().getId()).get();
            indicadorRepository.save(indicador);
            analisisEconomico.setAumentoVentas(indicador);

            indicador = indicadorRepository.findById(analisisEconomico.getAccesoOtrasFuentes().getId()).get();
            indicadorRepository.save(indicador);
            analisisEconomico.setAccesoOtrasFuentes(indicador);

            indicador = indicadorRepository.findById(analisisEconomico.getEmpleosFormales().getId()).get();
            indicadorRepository.save(indicador);
            analisisEconomico.setEmpleosFormales(indicador);

            indicador = indicadorRepository.findById(analisisEconomico.getEmpleosInformales().getId()).get();
            indicadorRepository.save(indicador);
            analisisEconomico.setEmpleosInformales(indicador);

            indicador = indicadorRepository.findById(analisisEconomico.getEmpleosNuevos().getId()).get();
            indicadorRepository.save(indicador);
            analisisEconomico.setEmpleosNuevos(indicador);

            indicador = indicadorRepository.findById(analisisEconomico.getEmpresaExportando().getId()).get();
            indicadorRepository.save(indicador);
            analisisEconomico.setEmpresaExportando(indicador);

            indicador = indicadorRepository.findById(analisisEconomico.getVentassExportacion().getId()).get();
            indicadorRepository.save(indicador);
            analisisEconomico.setVentassExportacion(indicador);

            indicador = indicadorRepository.findById(analisisEconomico.getDiversificacionProductos().getId()).get();
            indicadorRepository.save(indicador);
            analisisEconomico.setDiversificacionProductos(indicador);

            indicador= indicadorRepository.findById(analisisEconomico.getAperturaNuevosMercados().getId()).get();
            indicadorRepository.save(indicador);
            analisisEconomico.setAperturaNuevosMercados(indicador);

        return ResponseEntity.status(HttpStatus.CREATED).body(analisisEconomicoRepository.save(analisisEconomico));

        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/planAccion")
    public ResponseEntity<?>updateAccion(@RequestBody Process process){
        
        return ResponseEntity.status(HttpStatus.CREATED).body(ProcessEmpresario.save(process));
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
