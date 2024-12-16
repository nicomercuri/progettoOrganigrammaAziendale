package com.example.progettoorganigrammaaziendale.composite;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface NodoIF extends Serializable {

    void aggiungiNodo(NodoIF nodo);
    void rimuoviNodo(NodoIF nodo);
    List<NodoIF> getFigli();

    String getNome();
    void setNome(String nome);

    void aggiungiRuolo(Ruolo ruolo);
    void rimuoviRuolo(Ruolo ruolo);
    List<Ruolo> getRuoli();

    void aggiungiDipendente(Dipendente dipendente, Ruolo ruolo);
    void rimuoviDipendente(Dipendente dipendente, Ruolo ruolo);
    Map<Dipendente,Ruolo> getDipendenti();
}
