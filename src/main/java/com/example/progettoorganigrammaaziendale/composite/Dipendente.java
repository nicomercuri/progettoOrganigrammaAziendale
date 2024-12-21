package com.example.progettoorganigrammaaziendale.composite;

import java.io.Serializable;
import java.util.Objects;

public class Dipendente implements Serializable {

    private String nome, cognome;

    public Dipendente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dipendente that = (Dipendente) o;
        return Objects.equals(nome, that.nome) && Objects.equals(cognome, that.cognome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome);
    }

    @Override
    public String toString() {
        return nome + " " + cognome;
    }
}