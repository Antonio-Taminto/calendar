package com.calendar.entities.DTO.request;

public class UpdateCalendarioRequestDTO {
    private String nome;
    private String descrizione;
    private String colore;

    public UpdateCalendarioRequestDTO(String colore, String descrizione, String nome) {
        this.colore = colore;
        this.descrizione = descrizione;
        this.nome = nome;
    }

    public UpdateCalendarioRequestDTO() {
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
