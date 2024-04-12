package com.calendar.entities.DTO.response;

import java.time.LocalDateTime;

public class EventoResponseDTO {
    private Long id;
    private String nome;
    private String descrizione;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;

    public EventoResponseDTO(String nome, Long id, String descrizione, LocalDateTime dataInizio, LocalDateTime dataFine) {
        this.nome = nome;
        this.id = id;
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public EventoResponseDTO() {
    }

    public LocalDateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
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
}
