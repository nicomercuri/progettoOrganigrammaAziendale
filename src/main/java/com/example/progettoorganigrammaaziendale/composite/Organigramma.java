package com.example.progettoorganigrammaaziendale.composite;

public class Organigramma {

    //questa classe mi serve per accedere al nodo radice dell'organigramma. Questo mi permette di accedere
    //a tutte le informazioni della gerarchia

    private NodoComposito nodoRadice;

    public Organigramma(String nomeNodoRadice) {
        this.nodoRadice = new NodoComposito(nomeNodoRadice);
    }

    public NodoComposito getNodoRadice() {
        return nodoRadice;
    }

    public void setNodoRadice(NodoComposito nodoRadice) {
        this.nodoRadice = nodoRadice;
    }
}
