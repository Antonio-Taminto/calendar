package com.calendar.entities.DTO.request;

import java.time.LocalDateTime;

public class UpdateEventoRequestDTO {
    private String nome;
    private String descrizione;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;

    public UpdateEventoRequestDTO(LocalDateTime dataFine, LocalDateTime dataInizio, String descrizione, String nome) {
        this.dataFine = dataFine;
        this.dataInizio = dataInizio;
        this.descrizione = descrizione;
        this.nome = nome;
    }

    public UpdateEventoRequestDTO() {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
