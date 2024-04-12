package com.calendar.mappers;

import com.calendar.entities.Calendario;
import com.calendar.entities.DTO.request.CreateCalendarioRequestDTO;
import com.calendar.entities.DTO.request.CreateUserRequestDTO;
import com.calendar.entities.DTO.response.CalendarioResponseDTO;
import com.calendar.entities.DTO.response.UserResponseDTO;
import com.calendar.entities.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;

    public User convertUserRequestToEntity(CreateUserRequestDTO createUserRequestDTO){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(createUserRequestDTO, User.class);
    }

    public UserResponseDTO convertUserEntityToResponse(User user){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(user, UserResponseDTO.class);
    }
    public List<UserResponseDTO> mapList(List<User> source) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return source
                .stream()
                .map(element -> modelMapper.map(element, UserResponseDTO.class))
                .collect(Collectors.toList());
    }
}
