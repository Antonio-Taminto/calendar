package com.calendar.mappers;

import com.calendar.entities.Calendario;
import com.calendar.entities.DTO.request.CreateCalendarioRequestDTO;
import com.calendar.entities.DTO.response.CalendarioResponseDTO;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CalendarioMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Calendario convertCalendarioRequestToEntity(CreateCalendarioRequestDTO createCalendarioRequestDTO){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(createCalendarioRequestDTO, Calendario.class);
    }

    public CalendarioResponseDTO convertCalendarioEntityToResponse(Calendario calendario){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(calendario, CalendarioResponseDTO.class);
    }
    public List<CalendarioResponseDTO> mapList(List<Calendario> source) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return source
                .stream()
                .map(element -> modelMapper.map(element, CalendarioResponseDTO.class))
                .collect(Collectors.toList());
    }
}
