package com.example.progettoorganigrammaaziendale.command;

import com.example.progettoorganigrammaaziendale.composite.NodoComposito;
import com.example.progettoorganigrammaaziendale.composite.Ruolo;

public class ComandoAggiungiRuolo implements ComandoIF{

    private NodoComposito nodo;
    private Ruolo ruolo;

    public ComandoAggiungiRuolo(NodoComposito nodo, Ruolo ruolo) {
        this.nodo = nodo;
        this.ruolo = ruolo;
    }

    @Override
    public void esegui() {
        nodo.aggiungiRuolo(ruolo);
    }

    @Override
    public void annulla() {
        nodo.rimuoviRuolo(ruolo);
    }
}
