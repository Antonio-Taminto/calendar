package com.calendar.controllers;

import com.calendar.entities.Calendario;
import com.calendar.entities.DTO.request.CreateCalendarioRequestDTO;
import com.calendar.entities.DTO.request.UpdateCalendarioRequestDTO;
import com.calendar.entities.DTO.response.CalendarioResponseDTO;
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
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = CalendarioResponseDTO.class), mediaType = "application/json") })
    @PostMapping("/crea/{userId}")
    public ResponseEntity<CalendarioResponseDTO> postCalendario(@RequestBody CreateCalendarioRequestDTO calendario,@PathVariable(name = "userId") Long userId){
        Optional<CalendarioResponseDTO> calendarioResponseDTOOptional = service.addCalendario(calendario,userId);
        if(calendarioResponseDTOOptional.isPresent()) {
            return ResponseEntity.ok().body(calendarioResponseDTOOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(
            summary = "Recupera la lista di tutti i Calendario presenti",
            description = "richiede la lista di Calendario." +
                    "La risposta è una lista di oggetto Calendario con id,nome,descrizione,colore,lista di eventi." ,
            tags = { "Calendario", "get" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = CalendarioResponseDTO.class), mediaType = "application/json") })
    @GetMapping("/prenditutti")
    public ResponseEntity<List<CalendarioResponseDTO>> getCalendari(){
        return ResponseEntity.ok().body(service.getCalendari());
    }
    @Operation(
            summary = "Recupera un Calendario dall'ID",
            description = "richiede un Calendario dato l'id." +
                    "La risposta è un oggetto Calendario con id,nome,descrizione,colore,lista di eventi." ,
            tags = { "Calendario", "get" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = CalendarioResponseDTO.class), mediaType = "application/json") })
    @GetMapping("/prendi/{id}")
    public ResponseEntity<CalendarioResponseDTO> getCalendario(@PathVariable Long id){
        Optional<CalendarioResponseDTO> calendarioOptional = service.getCalendarioById(id);
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
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = CalendarioResponseDTO.class), mediaType = "application/json") })
    @PutMapping("/modifica/{id}")
    public ResponseEntity<CalendarioResponseDTO> putCalendario(@PathVariable Long id,@RequestBody UpdateCalendarioRequestDTO calendarioRequestDTO){
        Optional<CalendarioResponseDTO> calendarioOptional = service.updateCalendario(calendarioRequestDTO,id);
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
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = CalendarioResponseDTO.class), mediaType = "application/json") })
    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<CalendarioResponseDTO> deleteCalendario(@PathVariable Long id){
        Optional<CalendarioResponseDTO> calendarioOptional = service.deleteCalendarioById(id);
        if(calendarioOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calendarioOptional.get());
    }

}
