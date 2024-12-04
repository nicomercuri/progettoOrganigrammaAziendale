package com.example.progettoorganigrammaaziendale.composite;

import java.util.Objects;

public class Dipendente {

    private String nome, cognome;
    private Ruolo ruolo; //devo fare attenzione perchè probabilmente un dipendente può avere più ruoli

    public Dipendente(String nome, String cognome, Ruolo ruolo) {
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

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
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
