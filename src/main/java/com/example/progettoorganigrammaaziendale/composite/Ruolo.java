package com.example.progettoorganigrammaaziendale.composite;

import java.io.Serializable;
import java.util.Objects;

public class Ruolo implements Serializable {

    private String nomeRuolo;

    public Ruolo(String nomeRuolo) {
        this.nomeRuolo = nomeRuolo;
    }

    public String getNomeRuolo() {
        return nomeRuolo;
    }

    public void setNomeRuolo(String nomeRuolo) {
        this.nomeRuolo = nomeRuolo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ruolo ruolo = (Ruolo) o;
        return Objects.equals(nomeRuolo, ruolo.nomeRuolo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nomeRuolo);
    }
}
