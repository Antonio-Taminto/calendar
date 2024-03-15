package com.calendar.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Calendario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    private String nome;
    @Column
    private String descrizione;
    @Column(nullable = false)
    private String colore;
    @OneToMany(mappedBy = "calendario")
    private List<Evento> eventoList;

    public Calendario(Long id, String nome, String descrizione, String colore, List<Evento> eventoList) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.colore = colore;
        this.eventoList = eventoList;
    }

    public Calendario() {
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }
}
