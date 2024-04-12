package com.calendar.entities.DTO.request;

import com.calendar.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CreateCalendarioRequestDTO {
    private String nome;
    private String descrizione;
    private String colore;

    public CreateCalendarioRequestDTO() {
    }

    public CreateCalendarioRequestDTO(String colore, String descrizione, String nome) {
        this.colore = colore;
        this.descrizione = descrizione;
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
