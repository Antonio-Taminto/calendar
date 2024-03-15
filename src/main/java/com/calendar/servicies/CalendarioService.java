package com.calendar.servicies;

import com.calendar.entities.Calendario;
import com.calendar.entities.Evento;
import com.calendar.repositories.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarioService {
    @Autowired
    private CalendarioRepository repository;
    @Autowired
    private EventoService eventoService;

    public Calendario addCalendario(Calendario calendario){
        //salviamo l'oggetto e poi lo ritorniamo
        return repository.save(calendario);
    }
    public List<Calendario> getCalendari(){
        //ritorniamo tutta la lista degli oggetti
        return repository.findAll();
    }
    public Optional<Calendario> getCalendarioById(Long id){
        //cerchiamo e poi ritorniamo l'oggetto tramite id
        return repository.findById(id);
    }
    public Optional<Calendario> updateCalendario(Calendario calendario, Long id){
        //recuperiamo l'oggetto da modificare grazie all'id

        Optional<Calendario> calendarioOptional = repository.findById(id);
        //controlliamo se l'oggetto è presente
        if(calendarioOptional.isPresent()){
            //modifichiamo tutti i parametri dell'oggetto
            calendarioOptional.get().setNome(calendario.getNome());
            calendarioOptional.get().setDescrizione(calendario.getDescrizione());
            calendarioOptional.get().setColore(calendario.getColore());
            //calendarioOptional.get().setEventoList(calendario.getEventoList());
            //salviamo l'oggetto aggiornato
            Calendario modifiedCalendario = repository.save(calendarioOptional.get());
            //ritorniamo l'oggetto che è stato aggiornato
            return Optional.of(modifiedCalendario);
        }else {
            //se non presente ritorniamo un oggetto vuoto
            return Optional.empty();
        }
    }
    public Optional<Calendario> deleteCalendarioById(Long id){
        //recuperiamo l'oggetto da eliminare tramite l'id
        Optional<Calendario> calendarioOptional = repository.findById(id);
        //controlliamo che l'oggetto sia presente
        if(calendarioOptional.isPresent()){
            //cancelliamo l'oggetto
            repository.delete(calendarioOptional.get());
            //ritorniamo l'oggetto cancellato
            return calendarioOptional;
        }else {
            return Optional.empty();
        }
    }
    public Optional<Calendario> addEventoToCalendario(Long id,Evento evento){
        Optional<Calendario> calendarioOptional = getCalendarioById(id);
        if(calendarioOptional.isPresent()){
            evento.setCalendario(calendarioOptional.get());
            eventoService.addEvento(evento);
            calendarioOptional.get().getEventoList().add(evento);
            Calendario calendarioWithNewEvento = repository.save(calendarioOptional.get());
            return Optional.of(calendarioWithNewEvento);
        }else{
            return Optional.empty();
        }
    }
}
