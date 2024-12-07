package com.example.progettoorganigrammaaziendale.command;

import com.example.progettoorganigrammaaziendale.composite.Dipendente;
import com.example.progettoorganigrammaaziendale.composite.NodoComposito;

public class ComandoAggiungiDipendente implements ComandoIF{

    private NodoComposito nodo;
    private Dipendente dipendente;

    public ComandoAggiungiDipendente(NodoComposito nodo, Dipendente dipendente) {
        this.nodo = nodo;
        this.dipendente = dipendente;
    }

    @Override
    public void esegui() {
        nodo.aggiungiDipendente(dipendente);
    }

    @Override
    public void annulla() {
        nodo.rimuoviDipendente(dipendente);
    }
}
