package com.calendar.entities.DTO.request;

import java.time.LocalDate;

public class CreateUserRequestDTO {
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;

    public CreateUserRequestDTO(String cognome, LocalDate dataDiNascita, String nome) {
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.nome = nome;
    }

    public CreateUserRequestDTO() {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
