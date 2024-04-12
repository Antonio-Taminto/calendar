package com.calendar.servicies;

import com.calendar.entities.Calendario;
import com.calendar.entities.DTO.request.CreateUserRequestDTO;
import com.calendar.entities.DTO.request.UpdateUserRequestDTO;
import com.calendar.entities.DTO.response.CalendarioResponseDTO;
import com.calendar.entities.DTO.response.UserResponseDTO;
import com.calendar.entities.Evento;
import com.calendar.entities.User;
import com.calendar.mappers.UserMapper;
import com.calendar.repositories.CalendarioRepository;
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
    private CalendarioRepository calendarioRepository;
    @Autowired
    private UserMapper userMapper;
    /**
     * Dato un CreateUserRequestDTO in ingresso viene convertito
     * in entity, salvato al db tramite la repository.
     * Viene convertita l'Entity in response e ritorna l'UserResponseDTO
     * @param createUserRequestDTO
     * @return Evento
     */
    public UserResponseDTO addUser(CreateUserRequestDTO createUserRequestDTO){
        //convertiamo con il mapper il CreateUserRequestDTO in User
        User user = userMapper.convertUserRequestToEntity(createUserRequestDTO);
        //salviamo sul db tramite la repository lo user
        User userSaved = repository.save(user);
        //convertiamo con il mapper l' entity User in response UserResponseDTO
        UserResponseDTO userResponseDTO = userMapper.convertUserEntityToResponse(userSaved);
        //ritorniamo la response
        return userResponseDTO;
    }
    /**
     * Richiede tutta la lista di User presenti
     * sul db tramite la repository.
     * Converte la lista di entity in response e la ritorna.
     * @return List</>
     */
    public List<UserResponseDTO> getAll(){
        //ritorniamo tutta la lista degli oggetti convertiti in response
        return userMapper.mapList(repository.findAll());
    }
    /**
     * Dato un Long in ingresso che rappresenta l'id dell' User
     * viene cercato l'User con il medesimo id tramite la repository,
     * se presente viene convertita l' entity in response e poi ritornata
     * in caso contrario viene ritornato un oggetto Optional vuoto.
     * @param id
     * @return Optional</>
     */
    public Optional<UserResponseDTO> getUserFromId(Long id){
        //cerchiamo e poi ritorniamo l'oggetto tramite id
        Optional<User> userOptional = repository.findById(id);
        //controlliamo che l'oggetto Optional sia presente
        if(userOptional.isPresent()){
            //se presente convertiamo l'entity in response
            UserResponseDTO response = userMapper.convertUserEntityToResponse(userOptional.get());
            //ritorniamo l'Optional della response
            return Optional.of(response);
        }else {
            //se non presente ritorniamo un oggetto Optional vuoto
            return Optional.empty();
        }
    }
    /**
     * Dato un Long id in ingresso viene richiesto al db
     * un User con il medesimo id,
     * se presente viene utilizzato l' UpdateUserRequestDTO preso in
     * ingresso per modificare tutti i dati dello User
     * viene salvato e successivamente convertito in response che viene ritornata.
     * Se invece non è presente viene ritornato un oggetto vuoto.
     * @param updateUserRequestDTO
     * @param id
     * @return Optional</>
     */
    public Optional<UserResponseDTO> updateUser(Long id, UpdateUserRequestDTO updateUserRequestDTO){
        //recuperiamo l'oggetto da modificare grazie all'id
        Optional<User> userOptional = repository.findById(id);
        //controlliamo se l'oggetto è presente
        if(userOptional.isPresent()){
            //modifichiamo tutti i parametri dell'oggetto
            userOptional.get().setNome(updateUserRequestDTO.getNome());
            userOptional.get().setCognome(updateUserRequestDTO.getCognome());
            userOptional.get().setDataDiNascita(updateUserRequestDTO.getDataDiNascita());
            //salviamo l'oggetto aggiornato
            User userUpdated = repository.save(userOptional.get());
            //convertiamo l'entity in response
            UserResponseDTO response = userMapper.convertUserEntityToResponse(userUpdated);
            //ritorniamo l'Optional della response
            return Optional.of(response);
        }else {
            //se non presente ritorniamo un oggetto vuoto
            return Optional.empty();
        }
    }
    /**
     * Dato un Long id in ingresso viene richiesto
     * al db uno User col medesimo id.
     * Se presente viene cancellato l'User dal db
     * e infine convertita l' entity in response e viene ritornata.
     * Se non presente viene ritornato un oggetto Optional vuoto.
     * @param id
     * @return Optional</>
     */
    public Optional<UserResponseDTO> deleteUserFromId(Long id){
        //recuperiamo l'oggetto da eliminare tramite l'id
        Optional<User> userOptional = repository.findById(id);
        //controlliamo che l'oggetto sia presente
        if(userOptional.isPresent()){
            //cancelliamo l'oggetto
            repository.delete(userOptional.get());
            //convertiamo l'entity in response
            UserResponseDTO response = userMapper.convertUserEntityToResponse(userOptional.get());
            //ritorniamo l'Optional della response
            return Optional.of(response);
        }else {
            //se non presente viene ritornato un oggetto vuoto
            return Optional.empty();
        }
    }
}
