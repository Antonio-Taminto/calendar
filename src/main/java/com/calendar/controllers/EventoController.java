package com.calendar.controllers;

import com.calendar.servicies.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.calendar.entities.Evento;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoService service;

    @PostMapping("/postevento")
    public ResponseEntity<Evento> postEvento(@RequestBody Evento evento){
        return ResponseEntity.ok().body(service.addEvento(evento));
    }
    @GetMapping("/geteventi")
    public ResponseEntity<List<Evento>> getEventi(){
        return ResponseEntity.ok().body(service.getEventi());
    }
    @GetMapping("/getevento/{id}")
    public ResponseEntity<Evento> getEvento(@PathVariable Long id){
        Optional<Evento> eventoOptional = service.getEvento(id);
        if(eventoOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(eventoOptional.get());
    }
    @PutMapping("/putevento/{id}")
    public ResponseEntity<Evento> updateEvento(
            @PathVariable Long id,
            @RequestBody Evento evento){
        Optional<Evento> eventoOptional = service.updateEvento(evento,id);
        if(eventoOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(eventoOptional.get());
    }
    @DeleteMapping("/deleteevento/{id}")
    public ResponseEntity<Evento> deleteEventoById(@PathVariable Long id){
        Optional<Evento> eventoOptional = service.deleteEventoById(id);
        if(eventoOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(eventoOptional.get());
    }
}
