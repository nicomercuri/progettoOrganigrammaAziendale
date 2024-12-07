package com.example.progettoorganigrammaaziendale.command;

import com.example.progettoorganigrammaaziendale.composite.Dipendente;
import com.example.progettoorganigrammaaziendale.composite.NodoComposito;

public class ComandoRimuoviDipendente implements ComandoIF {
    private NodoComposito nodo;
    private Dipendente dipendente;

    public ComandoRimuoviDipendente(NodoComposito nodo, Dipendente dipendente) {
        this.nodo = nodo;
        this.dipendente = dipendente;
    }

    @Override
    public void esegui() {
        nodo.rimuoviDipendente(dipendente);
    }

    @Override
    public void annulla() {
        nodo.aggiungiDipendente(dipendente);
    }
}
