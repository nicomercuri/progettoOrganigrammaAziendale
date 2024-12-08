package com.example.progettoorganigrammaaziendale.memento;

import com.example.progettoorganigrammaaziendale.composite.NodoComposito;
import java.io.Serializable;

public class Memento implements Serializable {

    private static final long serialVersionUID = 1L;

    private final NodoComposito nodoRadice; //mi salvo il nodo radice in modo che possa accedere a tutti i suoi figli

    public Memento(NodoComposito nodoRadice) {
        this.nodoRadice = nodoRadice;
    }

    public NodoComposito getNodoRadice() {
        return nodoRadice;
    }
}
