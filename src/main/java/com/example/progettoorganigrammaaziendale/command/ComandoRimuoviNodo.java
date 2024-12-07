package com.example.progettoorganigrammaaziendale.command;

import com.example.progettoorganigrammaaziendale.composite.NodoComposito;
import com.example.progettoorganigrammaaziendale.composite.NodoIF;

import java.util.ArrayList;
import java.util.List;

public class ComandoRimuoviNodo implements ComandoIF{

    private NodoComposito nodoPadre;
    private NodoComposito nodoRimosso;
    private List<NodoIF> figliSalvati; //mi serve per ripristinarli dopo l'undo

    public ComandoRimuoviNodo(NodoComposito nodoPadre, NodoComposito nodoRimosso) {
        this.nodoPadre = nodoPadre;
        this.nodoRimosso = nodoRimosso;
        this.figliSalvati = new ArrayList<>(nodoRimosso.getFigli());
    }

    @Override
    public void esegui() {
        nodoPadre.rimuoviNodo(nodoRimosso);
    }

    @Override
    public void annulla() {
        nodoRimosso.getFigli().addAll(figliSalvati);
        nodoPadre.aggiungiNodo(nodoRimosso);
    }
}
