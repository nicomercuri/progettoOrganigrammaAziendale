package com.example.progettoorganigrammaaziendale.composite;

import com.example.progettoorganigrammaaziendale.memento.Memento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Organigramma implements Serializable {

    //questa classe mi serve per accedere al nodo radice dell'organigramma. Questo mi permette di accedere
    //a tutte le informazioni della struttura

    private NodoComposito nodoRadice;

    public Organigramma(String nomeNodoRadice) {
        this.nodoRadice = new NodoComposito(nomeNodoRadice);
    }

    public NodoComposito getNodoRadice() {
        return nodoRadice;
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

    public NodoComposito trovaPadre(NodoComposito nodo) {
        if(nodoRadice == nodo)
            return null; //in quanto la radice non ha un padre
        return nodoRadice.trovaPadre(nodo);
    }

    public Memento creaMemento(){
        return new Memento(copiaOrganigramma(nodoRadice));
    }

    public void ripristinaMemento(Memento memento){
        this.nodoRadice = memento.getNodoRadice();
    }


    private NodoComposito copiaOrganigramma(NodoComposito nodoRadice) {
        NodoComposito copia = new NodoComposito(nodoRadice.getNome());
        for(NodoIF nodoFiglio: nodoRadice.getFigli())
            copia.aggiungiNodo(copiaOrganigramma((NodoComposito) nodoFiglio));
        for(Ruolo ruolo: nodoRadice.getRuoli())
            copia.aggiungiRuolo(new Ruolo(ruolo.getNomeRuolo()));
        for(Dipendente dipendente: nodoRadice.getDipendenti().keySet()) {
            Ruolo ruolo = nodoRadice.getDipendenti().get(dipendente);
            copia.aggiungiDipendente(new Dipendente(dipendente.getNome(), dipendente.getCognome()), new Ruolo(ruolo.getNomeRuolo()));
        }
        return copia;
    }

}