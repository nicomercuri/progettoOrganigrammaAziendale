package com.example.progettoorganigrammaaziendale.command;

import com.example.progettoorganigrammaaziendale.composite.NodoComposito;
import com.example.progettoorganigrammaaziendale.composite.NodoIF;

import java.util.List;

public class ComandoRimuoviNodo implements ComandoIF{

    private NodoComposito nodoPadre;
    private NodoComposito nodoRimosso;

    public ComandoRimuoviNodo(NodoComposito nodoPadre, NodoComposito nodoRimosso) {
        this.nodoPadre = nodoPadre;
        this.nodoRimosso = nodoRimosso;
    }

    @Override
    public void esegui() {
        nodoPadre.rimuoviNodo(nodoRimosso);
    }

    @Override
    public void annulla() {
        nodoPadre.aggiungiNodo(nodoRimosso);
        //capire come recuperare i nodi figli
    }
}
