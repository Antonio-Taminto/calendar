package com.calendar.entities.DTO.response;

import com.calendar.entities.Calendario;

import java.time.LocalDate;
import java.util.List;

public class UserResponseDTO {
    private Long id;
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private List<CalendarioResponseDTO> calendarioList;

    public UserResponseDTO(List<CalendarioResponseDTO> calendarioList, String cognome, LocalDate dataDiNascita, Long id, String nome) {
        this.calendarioList = calendarioList;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.id = id;
        this.nome = nome;
    }

    public UserResponseDTO() {
    }

    public List<CalendarioResponseDTO> getCalendarioList() {
        return calendarioList;
    }

    public void setCalendarioList(List<CalendarioResponseDTO> calendarioList) {
        this.calendarioList = calendarioList;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
