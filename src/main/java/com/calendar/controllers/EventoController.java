package com.calendar.controllers;

import com.calendar.entities.Calendario;
import com.calendar.servicies.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "crea e salva un nuovo Evento",
            description = "richiede un nuovo Evento in formato JSON." +
                    "Salva nel database l'oggetto richiesto."+
                    "La risposta è l' oggetto Evento appena creato con id,nome,descrizione,data inizio,data fine." ,
            tags = { "Evento", "post" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendario.class), mediaType = "application/json") })
    @PostMapping("/crea")
    public ResponseEntity<Evento> postEvento( @RequestBody Evento evento){
        return ResponseEntity.ok().body(service.addEvento(evento));
    }
    @Operation(
            summary = "Recupera la lista di tutti gli Evento presenti",
            description = "richiede la lista di Evento." +
                    "La risposta è una lista di oggetto Evento con id,nome,descrizione,data inizio,data fine." ,
            tags = { "Evento", "get" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendario.class), mediaType = "application/json") })
    @GetMapping("/prenditutti")
    public ResponseEntity<List<Evento>> getEventi(){
        return ResponseEntity.ok().body(service.getEventi());
    }
    @Operation(
            summary = "Recupera un Evento dall'ID",
            description = "richiede un Evento dato l'id." +
                    "La risposta è un oggetto Evento con id,nome,descrizione,data inizio,data fine." ,
            tags = { "Evento", "get" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendario.class), mediaType = "application/json") })
    @GetMapping("/prendi/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id){
        Optional<Evento> eventoOptional = service.getEvento(id);
        if(eventoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(eventoOptional.get());
    }
    @Operation(
            summary = "Recupera un Evento dall'ID e richiede i dati da modificare in JSON",
            description = "richiede un Evento dato l'id,e i dati da modificare in JSON."+
                    "Recupera l' Evento dall'id,lo modifica con i dati in ingresso e salva l' Evento modificato" +
                    "La risposta è l' oggetto Evento modificato con id,nome,descrizione,data inizio,data fine." ,
            tags = { "Evento", "put" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendario.class), mediaType = "application/json") })
    @PutMapping("/modifica/{id}")
    public ResponseEntity<Evento> updateEvento(
            @PathVariable Long id,
            @RequestBody Evento evento){
        Optional<Evento> eventoOptional = service.updateEvento(evento,id);
        if(eventoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(eventoOptional.get());
    }
    @Operation(
            summary = "Recupera ed elimina un Evento dall'ID",
            description = "richiede un Evento dato l'id." +
                    "una volta recuperato l' Evento lo elimina " +
                    "La risposta è l' oggetto Evento appena eliminato con id,nome,descrizione,data inizio,data fine." ,
            tags = { "Evento", "delete" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendario.class), mediaType = "application/json") })
    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<Evento> deleteEventoById(@PathVariable Long id){
        Optional<Evento> eventoOptional = service.deleteEventoById(id);
        if(eventoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(eventoOptional.get());
    }
}
