package com.calendar.mappers;

import com.calendar.entities.DTO.request.CreateEventoRequestDTO;
import com.calendar.entities.DTO.response.EventoResponseDTO;
import com.calendar.entities.Evento;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventoMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Evento convertEventoRequestToEntity(CreateEventoRequestDTO createEventoRequestDTO){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(createEventoRequestDTO, Evento.class);
    }

    public EventoResponseDTO convertEventoEntityToResponse(Evento evento){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(evento, EventoResponseDTO.class);
    }
    public List<EventoResponseDTO> mapList(List<Evento> source) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return source
                .stream()
                .map(element -> modelMapper.map(element, EventoResponseDTO.class))
                .collect(Collectors.toList());
    }
}
