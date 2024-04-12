package com.calendar.entities.DTO.response;

import com.calendar.entities.Evento;

import java.util.List;

public class CalendarioResponseDTO {
    private Long id;
    private String nome;
    private String descrizione;
    private String colore;
    private List<EventoResponseDTO> eventoList;

    public CalendarioResponseDTO(String nome, Long id, String descrizione, String colore,List<EventoResponseDTO> eventoList) {
        this.nome = nome;
        this.id = id;
        this.descrizione = descrizione;
        this.colore = colore;
        this.eventoList = eventoList;
    }

    public CalendarioResponseDTO() {
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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

    public List<EventoResponseDTO> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<EventoResponseDTO> eventoList) {
        this.eventoList = eventoList;
    }
}
