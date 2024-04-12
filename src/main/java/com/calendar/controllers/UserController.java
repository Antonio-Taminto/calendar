package com.calendar.controllers;

import com.calendar.entities.Calendario;
import com.calendar.entities.DTO.request.CreateUserRequestDTO;
import com.calendar.entities.DTO.request.UpdateUserRequestDTO;
import com.calendar.entities.DTO.response.UserResponseDTO;
import com.calendar.entities.Evento;
import com.calendar.entities.User;
import com.calendar.servicies.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Tag(name = "User" ,description = "controller delle APIs per gli User")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Operation(
            summary = "crea e salva un nuovo User",
            description = "richiede un nuovo User in formato JSON." +
                    "Salva nel database l'oggetto richiesto."+
                    "La risposta è l' User Calendario appena creato con id,nome,data di nascita, lista di calendari." ,
            tags = { "User", "post" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponseDTO.class), mediaType = "application/json") })
    @PostMapping("/crea")
    public ResponseEntity<UserResponseDTO> postUser(@RequestBody CreateUserRequestDTO createUserRequestDTO){
        return ResponseEntity.ok().body(service.addUser(createUserRequestDTO));
    }
    @Operation(
            summary = "Recupera la lista di tutti gli User presenti",
            description = "richiede la lista di User." +
                    "La risposta è una lista di oggetto User con id,nome,data di nascita, lista di calendari." ,
            tags = { "User", "get" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponseDTO.class), mediaType = "application/json") })
    @GetMapping("/prenditutti")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok().body(service.getAll());
    }
    @Operation(
            summary = "Recupera uno User dall'ID",
            description = "richiede uno User dato l'id." +
                    "La risposta è un oggetto User con id,nome,data di nascita, lista di calendari." ,
            tags = { "User", "get" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponseDTO.class), mediaType = "application/json") })
    @GetMapping("/prendi/{id}")
    public ResponseEntity<UserResponseDTO> getFromId(@PathVariable Long id){
        Optional<UserResponseDTO> userOptional = service.getUserFromId(id);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }
    @Operation(
            summary = "Recupera uno User dall'ID e richiede i dati da modificare in JSON",
            description = "richiede uno User dato l'id,e i dati da modificare in JSON."+
                    "Recupera l' User dall'id,lo modifica con i dati in ingresso e salva l' User modificato" +
                    "La risposta è l' oggetto User appena modificato con id,nome,data di nascita, lista di calendari." ,
            tags = { "User", "put" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponseDTO.class), mediaType = "application/json") })
    @PutMapping("/modifica/{id}")
    public ResponseEntity<UserResponseDTO> putUser(@PathVariable Long id , @RequestBody UpdateUserRequestDTO updateUserRequestDTO){
        Optional<UserResponseDTO> userOptional = service.updateUser(id,updateUserRequestDTO);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }
    @Operation(
            summary = "Recupera ed elimina uno User dall'ID",
            description = "richiede uuno User dato l'id." +
                    "una volta recuperato l' User lo elimina " +
                    "La risposta è l' oggetto User appena eliminato con id,nome,data di nascita, lista di calendari." ,
            tags = { "User", "delete" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponseDTO.class), mediaType = "application/json") })
    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<UserResponseDTO> deleteFromId(@PathVariable Long id){
        Optional<UserResponseDTO> userOptional = service.deleteUserFromId(id);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }

}
