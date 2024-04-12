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
     * dato un User in ingresso viene salvato
     * al db tramite la repository.
     * @param createUserRequestDTO
     * @return Evento
     */
    public UserResponseDTO addUser(CreateUserRequestDTO createUserRequestDTO){
        User user = userMapper.convertUserRequestToEntity(createUserRequestDTO);
        User userSaved = repository.save(user);
        UserResponseDTO userResponseDTO = userMapper.convertUserEntityToResponse(userSaved);
        return userResponseDTO;
    }
    /**
     * richiede tutta la lista di User presenti
     * sul db tramite la repository.
     * Successivamente ritorna la lista di User presenti sul db.
     * @return List</>
     */
    public List<UserResponseDTO> getAll(){
        //ritorniamo tutta la lista degli oggetti convertiti in response
        return userMapper.mapList(repository.findAll());
    }
    /**
     * dato un Long in ingresso che rappresenta l'id dell'User
     * viene cercato l'User con il medesimo id tramite la repository,
     * prima di ritornare l'User viene controllato che
     * l'oggetto sia presente,
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
     * se presente viene utilizzato l' User preso in
     * ingresso per modificare tutti i dati dell'evento
     * e infine viene ritornato l'oggetto User modificato.
     * se invece non è presente viene ritornato un oggetto vuoto.
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
     * dato un Long id in ingresso viene richiesto
     * al db un User col medesimo id.
     * se presente viene cancellato l'User dal db
     * e infine viene ritornato l'User eliminato.
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
