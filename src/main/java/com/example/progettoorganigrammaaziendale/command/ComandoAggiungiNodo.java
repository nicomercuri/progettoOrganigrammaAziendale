package com.example.progettoorganigrammaaziendale.command;

import com.example.progettoorganigrammaaziendale.composite.NodoComposito;

public class ComandoAggiungiNodo implements ComandoIF{

    private NodoComposito nodoPadre;
    private NodoComposito nodoAggiunto;

    public ComandoAggiungiNodo(NodoComposito nodoPadre, String nomeNuovoNodo) {
        this.nodoPadre = nodoPadre;
        this.nodoAggiunto = new NodoComposito(nomeNuovoNodo);
    }

    @Override
    public void esegui() {
        nodoPadre.aggiungiNodo(nodoAggiunto);
    }

    @Override
    public void annulla() {
        nodoPadre.rimuoviNodo(nodoAggiunto);
    }
}
