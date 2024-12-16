package com.example.progettoorganigrammaaziendale.command;

import com.example.progettoorganigrammaaziendale.composite.Dipendente;
import com.example.progettoorganigrammaaziendale.composite.NodoComposito;
import com.example.progettoorganigrammaaziendale.composite.Ruolo;

public class ComandoAggiungiDipendente implements ComandoIF{

    private NodoComposito nodo;
    private Dipendente dipendente;
    private Ruolo ruolo;

    public ComandoAggiungiDipendente(NodoComposito nodo, Dipendente dipendente, Ruolo ruolo) {
        this.nodo = nodo;
        this.dipendente = dipendente;
        this.ruolo = ruolo;
    }

    @Override
    public void esegui() {
        nodo.aggiungiDipendente(dipendente,ruolo);
    }

    @Override
    public void annulla() {
        nodo.rimuoviDipendente(dipendente,ruolo);
    }
}
