package com.calendar.servicies;

import com.calendar.entities.DTO.CreateEventoRequestDTO;
import com.calendar.entities.DTO.EventoResponseDTO;
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

    /**
     * dato un Evento in ingresso viene salvato
     * al db tramite la repository.
     * @param createEventoRequestDTO
     * @return Evento
     */
    public EventoResponseDTO addEvento(CreateEventoRequestDTO createEventoRequestDTO){
        Evento evento = new Evento();
        evento.setNome(createEventoRequestDTO.getNome());
        evento.setDescrizione(createEventoRequestDTO.getDescrizione());
        evento.setDataInizio(createEventoRequestDTO.getDataInizio());
        evento.setDataFine(createEventoRequestDTO.getDataFine());

        Evento savedEvento = repository.save(evento);
        EventoResponseDTO eventoResponseDTO = new EventoResponseDTO();
        eventoResponseDTO.setId(savedEvento.getId());
        eventoResponseDTO.setNome(savedEvento.getNome());
        eventoResponseDTO.setDescrizione(savedEvento.getDescrizione());
        eventoResponseDTO.setDataInizio(savedEvento.getDataInizio());
        eventoResponseDTO.setDataFine(savedEvento.getDataFine());
        return eventoResponseDTO;
    }

    /**
     * richiede tutta la lista di Evento presenti
     * sul db tramite la repository.
     * Successivamente ritorna la lista di Evento presenti sul db.
     * @return List</>
     */
    public List<Evento> getEventi(){
        //ritorniamo tutta la lista degli oggetti
        return repository.findAll();
    }

    /**
     * dato un Long in ingresso che rappresenta l'id dell'Evento
     * viene cercato l'Evento con il medesimo id tramite la repository,
     * prima di ritornare l'Evento viene controllato che
     * l'oggetto sia presente,
     * in caso contrario viene ritornato un oggetto Optional vuoto.
     * @param id
     * @return Optional</>
     */
    public Optional<Evento> getEvento(Long id){
        //cerchiamo l'oggetto tramite id
        Optional<Evento> eventoOptional = repository.findById(id);
        //controlliamo che l'oggetto Optional sia presente
        if(eventoOptional.isPresent()){
            //se presente ritorniamo l'oggetto Optional
            return eventoOptional;
        }else {
            //se non presente ritorniamo un oggetto Optional vuoto
            return Optional.empty();
        }
    }

    /**
     * Dato un Long id in ingresso viene richiesto al db
     * un Evento con il medesimo id,
     * se presente viene utilizzato l'Evento preso in
     * ingresso per modificare tutti i dati dell'evento
     * e infine viene ritornato l'oggetto Evento modificato.
     * se invece non è presente viene ritornato un oggetto vuoto.
     * @param evento
     * @param id
     * @return Optional</>
     */
    public Optional<Evento> updateEvento(Evento evento,Long id) {
        //recuperiamo l'oggetto da modificare grazie all'id
        Optional<Evento> eventoOptional = getEvento(id);
        //controlliamo se l'oggetto è presente
        if (eventoOptional.isPresent()) {
            //modifichiamo tutti i parametri dell'oggetto
            eventoOptional.get().setNome(evento.getNome());
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

    /**
     * dato un Long id in ingresso viene richiesto
     * al db un Evento col medesimo id.
     * se presente viene cancellato l'Evento dal db
     * e infine viene ritornato l'Evento eliminato.
     * Se non presente viene ritornato un oggetto Optional vuoto.
     * @param id
     * @return Optional</>
     */
    public Optional<Evento> deleteEventoById(Long id){
        //recuperiamo l'oggetto da eliminare tramite l'id
        Optional<Evento> eventoOptional = getEvento(id);
        //controlliamo che l'oggetto sia presente
        if(eventoOptional.isPresent()){
            //cancelliamo l'oggetto
            repository.delete(eventoOptional.get());
            //ritorniamo l'oggetto cancellato
            return eventoOptional;
        }else{
            //se non presente viene ritornato un oggetto vuoto
            return Optional.empty();
        }

    }
}
