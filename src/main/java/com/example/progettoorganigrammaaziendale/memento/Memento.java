package com.example.progettoorganigrammaaziendale.memento;

import com.example.progettoorganigrammaaziendale.composite.NodoComposito;
import java.io.Serial;
import java.io.Serializable;

public class Memento implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final NodoComposito nodoRadice; //mi salvo il nodo radice in modo che possa accedere a tutti i suoi figli

    //per rispettare il pattern Memento, il costruttore e getNodoRadice NON dovrebbero essere public ma purtroppo
    //l'originator (Organigramma) sta in un altro package

    public Memento(NodoComposito nodoRadice) {
        this.nodoRadice = nodoRadice;
    }

    public NodoComposito getNodoRadice() {
        return nodoRadice;
    }
}
