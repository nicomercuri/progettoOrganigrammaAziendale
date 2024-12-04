package com.example.progettoorganigrammaaziendale.composite;

import java.io.Serializable;
import java.util.List;

public interface NodoIF extends Serializable {

    void aggiungiNodo(NodoIF nodo);
    void rimuoviNodo(NodoIF nodo);
    List<NodoIF> getFigli();

    String getNome();
    void setNome(String nome);

    void aggiungiRuolo(Ruolo ruolo);
    void rimuoviRuolo(Ruolo ruolo);
    List<Ruolo> getRuoli();

    void aggiungiDipendente(Dipendente dipendente);
    void rimuoviDipendente(Dipendente dipendente);
    List<Dipendente> getDipendenti();
}
