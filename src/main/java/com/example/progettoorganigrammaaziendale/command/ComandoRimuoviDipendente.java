package com.example.progettoorganigrammaaziendale.command;

import com.example.progettoorganigrammaaziendale.composite.Dipendente;
import com.example.progettoorganigrammaaziendale.composite.NodoComposito;
import com.example.progettoorganigrammaaziendale.composite.Ruolo;

public class ComandoRimuoviDipendente implements ComandoIF {
    private NodoComposito nodo;
    private Dipendente dipendente;
    private Ruolo ruolo;

    public ComandoRimuoviDipendente(NodoComposito nodo, Dipendente dipendente, Ruolo ruolo) {
        this.nodo = nodo;
        this.dipendente = dipendente;
        this.ruolo = ruolo;
    }

    @Override
    public void esegui() {
        nodo.rimuoviDipendente(dipendente,ruolo);
    }

    @Override
    public void annulla() {
        nodo.aggiungiDipendente(dipendente,ruolo);
    }
}
