package com.calendar.servicies;

import com.calendar.entities.Calendario;
import com.calendar.entities.Evento;
import com.calendar.entities.User;
import com.calendar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private CalendarioService calendarioService;

    public User addUser(User user){
        return repository.save(user);
    }
    public List<User> getAll(){
        return repository.findAll();
    }
    public Optional<User> getUserFromId(Long id){
        Optional<User> userOptional = repository.findById(id);
        if(userOptional.isPresent()){
            return userOptional;
        }else {
            return Optional.empty();
        }
    }
    public Optional<User> updateUser(Long id,User user){
        Optional<User> userOptional = getUserFromId(id);
        if(userOptional.isPresent()){
            userOptional.get().setNome(user.getNome());
            userOptional.get().setCognome(user.getCognome());
            userOptional.get().setDataDiNascita(user.getDataDiNascita());
            //userOptional.get().setCalendarioList(user.getCalendarioList());
            User userUpdated = repository.save(userOptional.get());
            return Optional.of(userUpdated);
        }else {
            return Optional.empty();
        }
    }
    public Optional<User> deleteUserFromId(Long id){
        Optional<User> userOptional = getUserFromId(id);
        if(userOptional.isPresent()){
            repository.delete(userOptional.get());
            return userOptional;
        }else {
            return Optional.empty();
        }
    }
    public Optional<User> addCalendarioToUser(Long idUser, Long idCalendario){
        Optional<User> userOptional = getUserFromId(idUser);
        if(userOptional.isPresent()){
            Optional<Calendario> calendarioOptional = calendarioService.getCalendarioById(idCalendario);
            if(calendarioOptional.isPresent()) {
                if(!userOptional.get().getCalendarioList().contains(calendarioOptional.get())) {
                    calendarioOptional.get().setUser(userOptional.get());
                    calendarioService.addCalendario(calendarioOptional.get());
                    return userOptional;
                }else {
                    return Optional.empty();
                }
            }else {
                return Optional.empty();
            }
        }else{
            return Optional.empty();
        }
    }
}
