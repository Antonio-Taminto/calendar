package com.calendar.controllers;

import com.calendar.entities.Calendario;
import com.calendar.entities.Evento;
import com.calendar.servicies.CalendarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Tag(name = "Calendario" ,description = "controller delle APIs per il calendario")
@RestController
@RequestMapping("/calendario")
public class CalendarioController {
    @Autowired
    private CalendarioService service;
    @Operation(
            summary = "crea e salva un nuovo Calendario",
            description = "richiede un nuovo Calendario in formato JSON." +
                    "Salva nel database l'oggetto richiesto."+
                    "La risposta è l' oggetto Calendario appena creato con id,nome,descrizione,colore,lista di eventi." ,
            tags = { "Calendario", "post" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendario.class), mediaType = "application/json") })
    @PostMapping("/crea")
    public ResponseEntity<Calendario> postCalendario(@RequestBody Calendario calendario){
        return ResponseEntity.ok().body(service.addCalendario(calendario));
    }
    @Operation(
            summary = "Recupera la lista di tutti i Calendario presenti",
            description = "richiede la lista di Calendario." +
                    "La risposta è una lista di oggetto Calendario con id,nome,descrizione,colore,lista di eventi." ,
            tags = { "Calendario", "get" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendario.class), mediaType = "application/json") })
    @GetMapping("/prenditutti")
    public ResponseEntity<List<Calendario>> getCalendari(){
        return ResponseEntity.ok().body(service.getCalendari());
    }
    @Operation(
            summary = "Recupera un Calendario dall'ID",
            description = "richiede un Calendario dato l'id." +
                    "La risposta è un oggetto Calendario con id,nome,descrizione,colore,lista di eventi." ,
            tags = { "Calendario", "get" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendario.class), mediaType = "application/json") })
    @GetMapping("/prendi/{id}")
    public ResponseEntity<Calendario> getCalendario(@PathVariable Long id){
        Optional<Calendario> calendarioOptional = service.getCalendarioById(id);
        if(calendarioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calendarioOptional.get());
    }
    @Operation(
            summary = "Recupera un Calendario dall'ID e richiede i dati da modificare in JSON",
            description = "richiede un Calendario dato l'id,e i dati da modificare in JSON."+
                    "Recupera il Calendario dall'id,lo modifica con i dati in ingresso e salva il Calendario modificato" +
                    "La risposta è l' oggetto Calendario modificato con id,nome,descrizione,colore,lista di eventi." ,
            tags = { "Calendario", "put" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendario.class), mediaType = "application/json") })
    @PutMapping("/modifica/{id}")
    public ResponseEntity<Calendario> putCalendario(@PathVariable Long id,@RequestBody Calendario calendario){
        Optional<Calendario> calendarioOptional = service.updateCalendario(calendario,id);
        if(calendarioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calendarioOptional.get());
    }
    @Operation(
            summary = "Recupera ed elimina un Calendario dall'ID",
            description = "richiede un Calendario dato l'id." +
                    "una volta recuperato il Calendario lo elimina " +
                    "La risposta è l' oggetto Calendario appena eliminato con id,nome,descrizione,colore,lista di eventi." ,
            tags = { "Calendario", "delete" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendario.class), mediaType = "application/json") })
    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<Calendario> deleteCalendario(@PathVariable Long id){
        Optional<Calendario> calendarioOptional = service.deleteCalendarioById(id);
        if(calendarioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calendarioOptional.get());
    }
    @Operation(
            summary = "Recupera un Calendario dall'id e gli aggiunge un Evento recuperato dall'id",
            description = "richiede un Calendario e un Evento dato l'id di entrambi." +
                    "recuperato il Calendario , viene recuperato l'Evento e salvato nella lista di eventi"+
                    "La risposta è un oggetto Calendario con id,nome,descrizione,colore,lista di eventi." ,
            tags = { "Calendario", "put" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendario.class), mediaType = "application/json") })
    @PutMapping("/aggiungievento/{id}/")
    public ResponseEntity<Calendario> addEvento(@PathVariable Long id, @RequestParam Long evento){
        Optional<Calendario> calendarioOptional = service.addEventoToCalendario(id,evento);
        if(calendarioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calendarioOptional.get());
    }

}
