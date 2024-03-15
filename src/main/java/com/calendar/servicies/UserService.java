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
    public Optional<User> addCalendarioToUser(Long id, Calendario calendario){
        Optional<User> userOptional = getUserFromId(id);
        if(userOptional.isPresent()){
            calendario.setUser(userOptional.get());
            calendarioService.addCalendario(calendario);
            userOptional.get().getCalendarioList().add(calendario);
            User userWithNewCalendario = repository.save(userOptional.get());
            return Optional.of(userWithNewCalendario);
        }else {
            return Optional.empty();
        }
    }
    public Optional<User> addEventoToUser(Long idUser,Long idCalendario, Evento evento){
        Optional<User> userOptional = getUserFromId(idUser);
        Optional<Calendario> calendarioOptional = Optional.empty();
        for(Calendario calendario : userOptional.get().getCalendarioList()){
            if(calendario.getId().equals(idCalendario)){
                calendarioOptional = Optional.of(calendario);
            }
        }
        if(userOptional.isPresent() && calendarioOptional.isPresent()){
            Optional<Calendario> calendarioWithNewEventoOpt = calendarioService.addEventoToCalendario(idCalendario,evento);
            if(calendarioWithNewEventoOpt.isPresent()){
                userOptional.get().getCalendarioList().forEach(calendario -> {
                    if(calendario.getId().equals(calendarioWithNewEventoOpt.get().getId())){
                        calendario = calendarioWithNewEventoOpt.get();
                    }
                });
            }else{
                return Optional.empty();
            }
            User userWithNewEvento = repository.save(userOptional.get());
            return Optional.of(userWithNewEvento);
        }else {
            return Optional.empty();
        }

    }
}
