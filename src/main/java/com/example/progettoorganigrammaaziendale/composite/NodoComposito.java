package com.example.progettoorganigrammaaziendale.composite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NodoComposito implements NodoIF, Serializable {

    private String nome;
    private List<NodoIF> figli;
    private List<Ruolo> ruoli;
    private List<Dipendente> dipendenti;

    public NodoComposito(String nome) {
        this.nome = nome;
        this.figli = new ArrayList<>();
        this.ruoli = new ArrayList<>();
        this.dipendenti = new ArrayList<>();
    }

    @Override
    public void aggiungiNodo(NodoIF nodo) {
        if(figli.contains(nodo))
            throw new IllegalArgumentException("Nodo già presente");
        figli.add(nodo);
    }

    @Override
    public void rimuoviNodo(NodoIF nodo) {
        figli.remove(nodo);
    }

    @Override
    public List<NodoIF> getFigli() {
        return new ArrayList<>(figli);
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void aggiungiRuolo(Ruolo ruolo) {
        ruoli.add(ruolo);
    }

    @Override
    public void rimuoviRuolo(Ruolo ruolo) {
        ruoli.remove(ruolo);
    }

    @Override
    public List<Ruolo> getRuoli() {
        return new ArrayList<>(ruoli);
    }

    @Override
    public void aggiungiDipendente(Dipendente dipendente) {
        dipendenti.add(dipendente);
    }

    @Override
    public void rimuoviDipendente(Dipendente dipendente) {
        dipendenti.remove(dipendente);
    }

    @Override
    public List<Dipendente> getDipendenti() {
        return new ArrayList<>(dipendenti);
    }
}
