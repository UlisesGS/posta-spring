package com.posta.crm.controller;

import com.posta.crm.entity.Process;
import com.posta.crm.service.canvas.process.IProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = ("*"))
@RequestMapping("/process")
@Api(tags = "seccion Procesos")
public class ProcessControllers {
    @Autowired
    private IProcessService processService;
    @GetMapping
    @ApiOperation("Listar todos los procesos")
    public ResponseEntity<?>findAll(){
        return ResponseEntity.ok().body(processService.findAll());

    }
    @GetMapping("/ultimo")
    public ResponseEntity<?>findAllUltimo(){
        return ResponseEntity.ok().body(processService.findAllUltimo());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        Optional<Process> optionalProcess = processService.findById(id);
        if(optionalProcess.isPresent()){
            return ResponseEntity.ok().body(optionalProcess.get());
        }
        return  ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?>save(@RequestBody  Process process){
        return ResponseEntity.status(HttpStatus.CREATED).body(processService.save(process));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>update(@RequestBody Process process, @PathVariable Long id){
        Optional<Process>optionalProcess= processService.findById(id);
        Process processDb=null;
        if(optionalProcess.isPresent()){
            processDb=optionalProcess.get();
            processDb.setEstado(process.getEstado());
            processDb.setSelfAssessment(process.getSelfAssessment());
            processDb.setCanvasModel(process.getCanvasModel());
            return ResponseEntity.status(HttpStatus.CREATED).body(processService.save(processDb));
        }
        return ResponseEntity.notFound().build();
    }
}
