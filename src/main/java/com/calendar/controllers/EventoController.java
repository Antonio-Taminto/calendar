package com.calendar.controllers;

import com.calendar.servicies.EventoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.calendar.entities.Evento;

import java.util.List;
import java.util.Optional;

@Tag(name = "Evento" ,description = "controller delle APIs per gli Eventi")
@RestController
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoService service;

    @PostMapping("/post")
    public ResponseEntity<Evento> postEvento( @RequestBody Evento evento){
        return ResponseEntity.ok().body(service.addEvento(evento));
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Evento>> getEventi(){
        return ResponseEntity.ok().body(service.getEventi());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id){
        Optional<Evento> eventoOptional = service.getEvento(id);
        if(eventoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(eventoOptional.get());
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<Evento> updateEvento(
            @PathVariable Long id,
            @RequestBody Evento evento){
        Optional<Evento> eventoOptional = service.updateEvento(evento,id);
        if(eventoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(eventoOptional.get());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Evento> deleteEventoById(@PathVariable Long id){
        Optional<Evento> eventoOptional = service.deleteEventoById(id);
        if(eventoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(eventoOptional.get());
    }
}
