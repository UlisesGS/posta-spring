package com.posta.crm.controller;

import com.posta.crm.entity.Process;
import com.posta.crm.service.canvas.process.IProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = ("*"))
@RequestMapping("/process")
public class ProcessControllers {
    @Autowired
    private IProcessService processService;
    @GetMapping
    public ResponseEntity<?>findAll(){
        return ResponseEntity.ok().body(processService.findAll());

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
            return ResponseEntity.status(HttpStatus.CREATED).body(processService.save(processDb));
        }
        return ResponseEntity.notFound().build();
    }
}
