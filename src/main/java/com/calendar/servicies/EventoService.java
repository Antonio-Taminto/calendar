package com.calendar.servicies;

import com.calendar.entities.Calendario;
import com.calendar.entities.DTO.request.CreateEventoRequestDTO;
import com.calendar.entities.DTO.request.UpdateEventoRequestDTO;
import com.calendar.entities.DTO.response.EventoResponseDTO;
import com.calendar.entities.Evento;
import com.calendar.mappers.EventoMapper;
import com.calendar.repositories.CalendarioRepository;
import com.calendar.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;
    @Autowired
    private EventoMapper eventoMapper;
    @Autowired
    private CalendarioRepository calendarioRepository;

    /**
     * Viene cercato il Calendario che vuole creare l'Evento, se presente
     * il CreateEventoRequestDTO viene convertito in entity,
     * viene settato il Calendario all'entity
     * che viene salvata al db tramite la repository.
     * In fine convertiamo l'entity salvata in CalendarioResponseDTO e ritornato l'optional
     * se l' idCalendario da in risposta un Calendario non presente viene ritornato un Optional empty.
     * @param createEventoRequestDTO
     * @return Evento
     */
    public Optional<EventoResponseDTO> addEvento(CreateEventoRequestDTO createEventoRequestDTO,Long idCalendario){
        //recuperiamo il Calendario dell'id
        Optional<Calendario> calendarioOptional = calendarioRepository.findById(idCalendario);
        //controlliamo se sia presente
        if(calendarioOptional.isPresent()) {
            //se presente convertiamo la request createEventoRequestDTO nell' entity Evento
            Evento evento = eventoMapper.convertEventoRequestToEntity(createEventoRequestDTO);
            //settiamo il calendario recuperato in precedenza all'evento
            evento.setCalendario(calendarioOptional.get());
            //salviamo l'evento nel db tramite la repository
            Evento savedEvento = repository.save(evento);
            //convertiamo l'entity appena salvata in response EventoResponseDTO
            EventoResponseDTO eventoResponseDTO = eventoMapper.convertEventoEntityToResponse(savedEvento);
            //ritorniamo l'Optional della response
            return Optional.of(eventoResponseDTO);
        }else {
            return Optional.empty();
        }
    }

    /**
     * Richiede tutta la lista ddi Evento presenti
     * sul db tramite la repository.
     * Successivamente converte la lista di entity in response
     * e ritorna la lista di EventoResponseDTO.
     * @return List</>
     */
    public List<EventoResponseDTO> getEventi(){
        //ritorniamo tutta la lista degli oggetti convertiti in una lista di response
        return eventoMapper.mapList(repository.findAll());
    }

    /**
     * Dato un Long in ingresso che rappresenta l'id dell'Evento
     * viene cercato l'Evento con il medesimo id tramite la repository,
     * viene controllato che l'oggetto sia presente e convertito in response,
     * va in return L'optional del EventoResponseDTO.
     * In caso contrario viene ritornato un oggetto Optional vuoto.
     * @param id
     * @return Optional</>
     */
    public Optional<EventoResponseDTO> getEvento(Long id){
        //cerchiamo l'oggetto tramite id
        Optional<Evento> eventoOptional = repository.findById(id);
        //controlliamo che l'oggetto Optional sia presente
        if(eventoOptional.isPresent()){
            //se presente convertiamo l'entity in response
            EventoResponseDTO response = eventoMapper.convertEventoEntityToResponse(eventoOptional.get());
            //ritorniamo l'Optional della response
            return Optional.of(response);
        }else {
            //se non presente ritorniamo un oggetto Optional vuoto
            return Optional.empty();
        }
    }

    /**
     * Dato un Long id in ingresso viene richiesto al db
     * un Evento con il medesimo id,
     * se presente viene utilizzato l' UpdateEventoRequestDTO preso in
     * ingresso per modificare tutti i dati dell' Evento Entity,
     * viene covertito l'oggetto Evento modificato in response,
     * e ritorna l'Optional del EventoResponseDTO.
     * Se invece non è presente viene ritornato un oggetto vuoto.
     * @param updateEventoRequestDTO
     * @param id
     * @return Optional</>
     */
    public Optional<EventoResponseDTO> updateEvento(UpdateEventoRequestDTO updateEventoRequestDTO, Long id) {
        //recuperiamo l'oggetto da modificare grazie all'id
        Optional<Evento> eventoOptional = repository.findById(id);
        //controlliamo se l'oggetto è presente
        if (eventoOptional.isPresent()) {
            //modifichiamo tutti i parametri dell'oggetto
            eventoOptional.get().setNome(updateEventoRequestDTO.getNome());
            eventoOptional.get().setDescrizione(updateEventoRequestDTO.getDescrizione());
            eventoOptional.get().setDataInizio(updateEventoRequestDTO.getDataInizio());
            eventoOptional.get().setDataFine(updateEventoRequestDTO.getDataFine());
            //salviamo l'oggetto aggiornato
            Evento eventoUpdated = repository.save(eventoOptional.get());
            //convertiamo l'entity in response
            EventoResponseDTO response = eventoMapper.convertEventoEntityToResponse(eventoUpdated);
            //ritorniamo l'Optional della response
            return Optional.of(response);
        } else {
            //se non presente ritorniamo un oggetto vuoto
            return Optional.empty();
        }
    }

    /**
     * Dato un Long id in ingresso viene richiesto
     * al db un Evento col medesimo id.
     * Se presente viene cancellato l'Evento dal db
     * viene convertita l'entity in response,
     * ritorna l' EventoResponseDTO.
     * Se non presente viene ritornato un oggetto Optional vuoto.
     * @param id
     * @return Optional</>
     */
    public Optional<EventoResponseDTO> deleteEventoById(Long id){
        //recuperiamo l'oggetto da eliminare tramite l'id
        Optional<Evento> eventoOptional = repository.findById(id);
        //controlliamo che l'oggetto sia presente
        if(eventoOptional.isPresent()){
            //cancelliamo l'oggetto
            repository.delete(eventoOptional.get());
            //convertiamo l'entity in response
            EventoResponseDTO response = eventoMapper.convertEventoEntityToResponse(eventoOptional.get());
            //ritorniamo l'Optional della response
            return Optional.of(response);
        }else{
            //se non presente viene ritornato un oggetto vuoto
            return Optional.empty();
        }

    }
}
