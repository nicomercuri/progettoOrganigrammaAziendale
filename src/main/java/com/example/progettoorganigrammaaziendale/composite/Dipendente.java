package com.example.progettoorganigrammaaziendale.composite;

import java.io.Serializable;
import java.util.Objects;

public class Dipendente implements Serializable {

    private String nome, cognome;
    private String ruolo; //devo fare attenzione perchè probabilmente un dipendente può avere più ruoli
    //l'ideale sarebbe avere una mappa nodo-ruolo (FARE IN FUTURO!!!!)

    public Dipendente(String nome, String cognome, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dipendente that = (Dipendente) o;
        return Objects.equals(nome, that.nome) && Objects.equals(cognome, that.cognome) && Objects.equals(ruolo, that.ruolo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome, ruolo);
    }

    @Override
    public String toString() {
        return "- " + ruolo + ": " + nome + " " + cognome;
    }
}
