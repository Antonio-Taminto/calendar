package com.calendar.servicies;

import com.calendar.entities.Evento;
import com.calendar.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    public Evento addEvento(Evento evento){
        //salviamo l'oggetto e poi lo ritorniamo
        return repository.save(evento);
    }
    public List<Evento> getEventi(){
        //ritorniamo tutta la lista degli oggetti
        return repository.findAll();
    }
    public Optional<Evento> getEvento(Long id){
        //cerchiamo e poi ritorniamo l'oggetto tramite id
        return repository.findById(id);
    }
    public Optional<Evento> updateEvento(Evento evento,Long id) {
        //recuperiamo l'oggetto da modificare grazie all'id
        Optional<Evento> eventoOptional = repository.findById(id);
        //se l'oggetto è presente
        if (eventoOptional.isPresent()) {
            //modifichiamo tutti i parametri dell'oggetto
            eventoOptional.get().setName(evento.getName());
            eventoOptional.get().setDescrizione(evento.getDescrizione());
            eventoOptional.get().setDataInizio(evento.getDataInizio());
            eventoOptional.get().setDataFine(evento.getDataFine());
            //salviamo l'oggetto aggiornato
            Evento eventoUpdated = repository.save(eventoOptional.get());
            //ritorniamo l'oggetto che è stato aggiornato
            return Optional.of(eventoUpdated);
        } else {
            //se non presente ritorniamo un oggetto vuoto
            return Optional.empty();
        }

    }
    public Optional<Evento> deleteEventoById(Long id){
        //recuperiamo l'oggetto da eliminare tramite l'id
        Optional<Evento> eventoOptional = repository.findById(id);
        //controlliamo che l'oggetto sia presente
        if(eventoOptional.isPresent()){
            //cancelliamo l'oggetto
            repository.delete(eventoOptional.get());
        }else{
            //se non presente viene ritornato un oggetto vuoto
            return Optional.empty();
        }
        //ritorniamo l'oggetto cancellato
        return eventoOptional;
    }
}
