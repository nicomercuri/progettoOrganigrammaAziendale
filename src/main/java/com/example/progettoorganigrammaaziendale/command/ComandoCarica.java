package com.example.progettoorganigrammaaziendale.command;

import com.example.progettoorganigrammaaziendale.composite.Organigramma;
import com.example.progettoorganigrammaaziendale.memento.GestoreSalvataggi;
import java.io.IOException;

public class ComandoCarica implements ComandoIF{

    private GestoreSalvataggi gestoreSalvataggi;
    private Organigramma organigramma;
    private String percorso;

    public ComandoCarica(GestoreSalvataggi gestoreSalvataggi, Organigramma organigramma, String percorso) {
        this.gestoreSalvataggi = gestoreSalvataggi;
        this.organigramma = organigramma;
        this.percorso = percorso;
    }

    @Override
    public void esegui() {
        try {
            gestoreSalvataggi.caricaDaFile(percorso, organigramma);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Errore durante il caricamento: " + e.getMessage());
        }
    }

    @Override
    public void annulla() {
        throw new UnsupportedOperationException("Impossibile annullare un carimento");
    }
}
