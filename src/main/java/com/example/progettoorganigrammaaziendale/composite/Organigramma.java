package com.example.progettoorganigrammaaziendale.composite;

import java.util.ArrayList;
import java.util.List;

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

    public void reset() {
        this.nodoRadice = null;
    }

    public String[] getNomiNodiPresenti() {
        List<String> nomi = new ArrayList<>();
        nomiRicorsivo(this.nodoRadice,nomi);
        return nomi.toArray(new String[0]);
    }

    private void nomiRicorsivo(NodoIF nodo, List<String> nomi) {
        nomi.add(nodo.getNome());
        for(NodoIF nodoFiglio: nodo.getFigli())
            nomiRicorsivo(nodoFiglio,nomi);
    }

    public NodoComposito restituisciNodoSelezionato(String nomeNodoSelezionato) {
        return cercaNodo(this.nodoRadice,nomeNodoSelezionato);
    }

    private NodoComposito cercaNodo(NodoIF nodo, String nomeNodoSelezionato) {
        if(nodo.getNome().equals(nomeNodoSelezionato))
            return (NodoComposito) nodo;
        for(NodoIF nodoFiglio: nodo.getFigli()) {
            NodoComposito nodoTrovato = cercaNodo(nodoFiglio, nomeNodoSelezionato);
            if(nodoTrovato != null)
                return nodoTrovato;
        }
        return null;
    }
}
