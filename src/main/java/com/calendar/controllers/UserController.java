package com.calendar.controllers;

import com.calendar.entities.Calendario;
import com.calendar.entities.Evento;
import com.calendar.entities.User;
import com.calendar.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/post")
    public ResponseEntity<User> postUser(@RequestBody User user){
        return ResponseEntity.ok().body(service.addUser(user));
    }
    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(service.getAll());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getFromId(@PathVariable Long id){
        Optional<User> userOptional = service.getUserFromId(id);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<User> putUser(@PathVariable Long id , @RequestBody User user){
        Optional<User> userOptional = service.updateUser(id,user);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteFromId(@PathVariable Long id){
        Optional<User> userOptional = service.deleteUserFromId(id);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }
    @PutMapping("/addcalendario/{id}")
    public ResponseEntity<User> putCalendario(@PathVariable Long id,@RequestBody Calendario calendario){
        Optional<User> userOptional = service.addCalendarioToUser(id,calendario);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }
    @PutMapping("/addevento/{idUser}/{idCalendario}")
    public ResponseEntity<User> putEvento(@PathVariable(name = "idUser") Long idUser,
                                          @PathVariable(name = "idCalendario") Long idCalendario,
                                          @RequestBody Evento evento){
        Optional<User> userOptional = service.addEventoToUser(idUser,idCalendario,evento);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }
}
