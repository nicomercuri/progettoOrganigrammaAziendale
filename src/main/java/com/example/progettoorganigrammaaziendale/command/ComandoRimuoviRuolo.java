package com.example.progettoorganigrammaaziendale.command;

import com.example.progettoorganigrammaaziendale.composite.NodoComposito;
import com.example.progettoorganigrammaaziendale.composite.Ruolo;

public class ComandoRimuoviRuolo implements ComandoIF{

    private NodoComposito nodo;
    private Ruolo ruolo;

    public ComandoRimuoviRuolo(NodoComposito nodo, Ruolo ruolo) {
        this.nodo = nodo;
        this.ruolo = ruolo;
    }

    @Override
    public void esegui() {
        nodo.rimuoviRuolo(ruolo);
    }

    @Override
    public void annulla() {
        nodo.aggiungiRuolo(ruolo);
    }
}
