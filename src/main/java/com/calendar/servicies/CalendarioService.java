package com.calendar.servicies;

import com.calendar.entities.Calendario;
import com.calendar.entities.DTO.request.CreateCalendarioRequestDTO;
import com.calendar.entities.DTO.request.UpdateCalendarioRequestDTO;
import com.calendar.entities.DTO.response.CalendarioResponseDTO;
import com.calendar.entities.Evento;
import com.calendar.entities.User;
import com.calendar.mappers.CalendarioMapper;
import com.calendar.repositories.CalendarioRepository;
import com.calendar.repositories.EventoRepository;
import com.calendar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarioService {
    @Autowired
    private CalendarioRepository calendarioRepository;
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private CalendarioMapper calendarioMapper;
    @Autowired
    private UserRepository userRepository;
    /**
     * Viene cercato l'User che vuole creare il Caledario, se presente
     * il CreateCalendarioRequestDTO viene convertito in entity,
     * viene settato l'User all'entity
     * che viene salvata al db tramite la repository.
     * In fine convertiamo l'entity salvata in CalendarioResponseDTO e ritornato l'optional
     * se l'User id non da in risposta uno User presente viene ritornato un Optional empty.
     * @param createCalendarioRequestDTO
     * @return Evento
     */
    public Optional<CalendarioResponseDTO> addCalendario(CreateCalendarioRequestDTO createCalendarioRequestDTO,Long userId){
        //recuperiamo l'user che vuole creare il calendario
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            //convertiamo la request in entity
            Calendario calendarioToSave = calendarioMapper.convertCalendarioRequestToEntity(createCalendarioRequestDTO);
            //settimo l'user all'entity
            calendarioToSave.setUser(userOptional.get());
            //salviamo l'entity
            Calendario savedCalendario = calendarioRepository.save(calendarioToSave);
            //convertiamo l'entity in response e la ritorniamo
            return Optional.of(calendarioMapper.convertCalendarioEntityToResponse(savedCalendario));
        }else {
            return Optional.empty();
        }
    }
    /**
     * Richiede tutta la lista di Calendario presenti
     * sul db tramite la repository.
     * Successivamente converte la lista di entity in response
     * e ritorna la lista di CalendarioResponseDTO.
     * @return List</>
     */
    public List<CalendarioResponseDTO> getCalendari(){
        //ritorniamo tutta la lista degli oggetti convertiti da entity a response
        return calendarioMapper.mapList(calendarioRepository.findAll());
    }
    /**
     * Dato un Long in ingresso che rappresenta l'id del Calendario
     * viene cercato il Calendario con il medesimo id tramite la repository,
     * viene controllato che l'oggetto sia presente e convertito in response,
     * va in return L'optional del CalendarioResponseDTO.
     * In caso contrario viene ritornato un oggetto Optional vuoto.
     * @param id
     * @return Optional</>
     */
    public Optional<CalendarioResponseDTO> getCalendarioById(Long id){
        //cerchiamo l'oggetto tramite id
        Optional<Calendario> calendarioOptional = calendarioRepository.findById(id);
        //controlliamo che l'oggetto Optional sia presente
        if(calendarioOptional.isPresent()){
            //se presente convertiamo l'entity in response
            CalendarioResponseDTO response = calendarioMapper.convertCalendarioEntityToResponse(calendarioOptional.get());
            //ritorniamo l'Optional della response
            return Optional.of(response);
        }else {
            //se non presente ritorniamo un oggetto Optional vuoto
            return Optional.empty();
        }
    }
    /**
     * Dato un Long id in ingresso viene richiesto al db
     * un Calendario con il medesimo id,
     * se presente viene utilizzato il UpdateCalendarioRequestDTO preso in
     * ingresso per modificare tutti i dati del Calendario Entity,
     * viene covertito l'oggetto Calendario modificato in response,
     * e ritorna l'Optional del CalendarioResponseDTO.
     * Se invece non è presente viene ritornato un oggetto vuoto.
     * @param calendarioRequestDTO
     * @param id
     * @return Optional</>
     */
    public Optional<CalendarioResponseDTO> updateCalendario(UpdateCalendarioRequestDTO calendarioRequestDTO, Long id){
        //recuperiamo l'oggetto da modificare grazie all'id
        Optional<Calendario> calendarioOptional = calendarioRepository.findById(id);
        //controlliamo se l'oggetto è presente
        if(calendarioOptional.isPresent()){
            //modifichiamo tutti i parametri dell'oggetto prendendoli dalla request
            calendarioOptional.get().setNome(calendarioRequestDTO.getNome());
            calendarioOptional.get().setDescrizione(calendarioRequestDTO.getDescrizione());
            calendarioOptional.get().setColore(calendarioRequestDTO.getColore());
            //salviamo l'oggetto aggiornato
            Calendario modifiedCalendario = calendarioRepository.save(calendarioOptional.get());
            //convertiamo l'entity in response
            CalendarioResponseDTO response = calendarioMapper.convertCalendarioEntityToResponse(modifiedCalendario);
            //ritorniamo l'Optional della response
            return Optional.of(response);
        }else {
            //se non presente ritorniamo un oggetto vuoto
            return Optional.empty();
        }
    }
    /**
     * Dato un Long id in ingresso viene richiesto
     * al db un Calendario col medesimo id.
     * Se presente viene cancellato il Calendario dal db
     * viene convertita l'entity in response,
     * ritorna il CalendarioResponseDTO.
     * Se non presente viene ritornato un oggetto Optional vuoto.
     * @param id
     * @return Optional</>
     */
    public Optional<CalendarioResponseDTO> deleteCalendarioById(Long id){
        //recuperiamo l'oggetto da eliminare tramite l'id
        Optional<Calendario> calendarioOptional = calendarioRepository.findById(id);
        //controlliamo che l'oggetto sia presente
        if(calendarioOptional.isPresent()){
            //cancelliamo l'oggetto
            calendarioRepository.delete(calendarioOptional.get());
            //convertiamo l'entity in response
            CalendarioResponseDTO response = calendarioMapper.convertCalendarioEntityToResponse(calendarioOptional.get());
            //ritorniamo l'Optional della response
            return Optional.of(response);
        }else {
            //se non presente viene ritornato un oggetto vuoto
            return Optional.empty();
        }
    }
}
