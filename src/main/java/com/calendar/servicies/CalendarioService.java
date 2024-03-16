package com.calendar.servicies;

import com.calendar.entities.Calendario;
import com.calendar.entities.Evento;
import com.calendar.entities.User;
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
    /**
     * dato un Calendario in ingresso viene salvato
     * al db tramite la repository.
     * @param calendario
     * @return Evento
     */
    public Calendario addCalendario(Calendario calendario){
        //salviamo l'oggetto e poi lo ritorniamo
        return repository.save(calendario);
    }
    /**
     * richiede tutta la lista di Calendario presenti
     * sul db tramite la repository.
     * Successivamente ritorna la lista di Calendario presenti sul db.
     * @return List</>
     */
    public List<Calendario> getCalendari(){
        //ritorniamo tutta la lista degli oggetti
        return repository.findAll();
    }
    /**
     * dato un Long in ingresso che rappresenta l'id del Calendario
     * viene cercato il Calendario con il medesimo id tramite la repository,
     * prima di ritornare il Calendario viene controllato che
     * l'oggetto sia presente,
     * in caso contrario viene ritornato un oggetto Optional vuoto.
     * @param id
     * @return Optional</>
     */
    public Optional<Calendario> getCalendarioById(Long id){
        //cerchiamo l'oggetto tramite id
        Optional<Calendario> calendarioOptional = repository.findById(id);
        //controlliamo che l'oggetto Optional sia presente
        if(calendarioOptional.isPresent()){
            //se presente ritorniamo l'oggetto Optional
            return calendarioOptional;
        }else {
            //se non presente ritorniamo un oggetto Optional vuoto
            return Optional.empty();
        }
    }
    /**
     * Dato un Long id in ingresso viene richiesto al db
     * un Calendario con il medesimo id,
     * se presente viene utilizzato il Calendario preso in
     * ingresso per modificare tutti i dati dell'evento
     * e infine viene ritornato l'oggetto Calendario modificato.
     * se invece non è presente viene ritornato un oggetto vuoto.
     * @param calendario
     * @param id
     * @return Optional</>
     */
    public Optional<Calendario> updateCalendario(Calendario calendario, Long id){
        //recuperiamo l'oggetto da modificare grazie all'id
        Optional<Calendario> calendarioOptional = getCalendarioById(id);
        //controlliamo se l'oggetto è presente
        if(calendarioOptional.isPresent()){
            //modifichiamo tutti i parametri dell'oggetto
            calendarioOptional.get().setNome(calendario.getNome());
            calendarioOptional.get().setDescrizione(calendario.getDescrizione());
            calendarioOptional.get().setColore(calendario.getColore());
            //salviamo l'oggetto aggiornato
            Calendario modifiedCalendario = repository.save(calendarioOptional.get());
            //ritorniamo l'oggetto che è stato aggiornato
            return Optional.of(modifiedCalendario);
        }else {
            //se non presente ritorniamo un oggetto vuoto
            return Optional.empty();
        }
    }
    /**
     * dato un Long id in ingresso viene richiesto
     * al db un Calendario col medesimo id.
     * se presente viene cancellato il Calendario dal db
     * e infine viene ritornato il Calendario eliminato.
     * Se non presente viene ritornato un oggetto Optional vuoto.
     * @param id
     * @return Optional</>
     */
    public Optional<Calendario> deleteCalendarioById(Long id){
        //recuperiamo l'oggetto da eliminare tramite l'id
        Optional<Calendario> calendarioOptional = getCalendarioById(id);
        //controlliamo che l'oggetto sia presente
        if(calendarioOptional.isPresent()){
            //cancelliamo l'oggetto
            repository.delete(calendarioOptional.get());
            //ritorniamo l'oggetto cancellato
            return calendarioOptional;
        }else {
            //se non presente viene ritornato un oggetto vuoto
            return Optional.empty();
        }
    }

    /**
     * Dato un Long idCalendario in ingresso si recupera un Calendario con il mededimo id,
     * se presente viene utilizzato l' altro Long idEvento per recuperare un Evento con il medesimo id,
     * se presente viene controllato che L'Evento con lo stesso id non sia già presente nella lista di Evento del Calendario,
     * se non presente viene effettuato il collegamento dell'Evento al Calendario.
     * Successivamente viene salvato l'Evento modificato e poi si ritorna il Calendario.
     * se qualsiasi condizione non sia vera viene ritornato un oggetto Optional vuoto.
     * @param idCalendario
     * @param idEvento
     * @return Optional</>
     */
    public Optional<Calendario> addEventoToCalendario(Long idCalendario,Long idEvento){
        //recuperiamo l'oggetto da eliminare tramite l'id
        Optional<Calendario> calendarioOptional = getCalendarioById(idCalendario);
        //controlliamo che l'oggetto sia presente
        if(calendarioOptional.isPresent()){
            //se presente recuperiamo l'oggetto da aggiungere tramite l'id
            Optional<Evento> eventoOptional = eventoService.getEvento(idEvento);
            //controlliamo se l'oggetto da aggiungere sia presente
            if(eventoOptional.isPresent()) {
                //se presente controlliamo se l'oggetto che vogliamo aggiungere non sia già presente nella lista
                if(!calendarioOptional.get().getEventoList().contains(eventoOptional.get())) {
                    //se non presente effettuiamo il collegamento con l'oggetto da aggiungere e l'oggetto a cui vogliamo aggiungerlo
                    eventoOptional.get().setCalendario(calendarioOptional.get());
                    //salviamo l'oggetto da aggiungere
                    eventoService.addEvento(eventoOptional.get());
                    //in fine ritorniamo il nostro oggetto
                    return calendarioOptional;
                }else {
                    //se non presente torniamo un oggetto optional vuoto
                    return Optional.empty();
                }
            }else {
                //se non presente torniamo un oggetto optional vuoto
                return Optional.empty();
            }
        }else{
            //se non presente torniamo un oggetto optional vuoto
            return Optional.empty();
        }
    }
}
