package com.calendar.controllers;

import com.calendar.entities.Calendario;
import com.calendar.entities.Evento;
import com.calendar.servicies.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/calendario")
public class CalendarioController {
    @Autowired
    private CalendarioService service;
    @PostMapping("/post")
    public ResponseEntity<Calendario> postCalendario(@RequestBody Calendario calendario){
        return ResponseEntity.ok().body(service.addCalendario(calendario));
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Calendario>> getCalendari(){
        return ResponseEntity.ok().body(service.getCalendari());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Calendario> getCalendario(@PathVariable Long id){
        Optional<Calendario> calendarioOptional = service.getCalendarioById(id);
        if(calendarioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calendarioOptional.get());
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<Calendario> putCalendario(@PathVariable Long id,@RequestBody Calendario calendario){
        Optional<Calendario> calendarioOptional = service.updateCalendario(calendario,id);
        if(calendarioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calendarioOptional.get());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Calendario> deleteCalendario(@PathVariable Long id){
        Optional<Calendario> calendarioOptional = service.deleteCalendarioById(id);
        if(calendarioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calendarioOptional.get());
    }
    @PutMapping("/addevento/{id}/")
    public ResponseEntity<Calendario> addEvento(@PathVariable Long id, @RequestParam Long evento){
        Optional<Calendario> calendarioOptional = service.addEventoToCalendario(id,evento);
        if(calendarioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calendarioOptional.get());
    }

}
