package com.example.progettoorganigrammaaziendale.command;

import com.example.progettoorganigrammaaziendale.composite.Organigramma;
import com.example.progettoorganigrammaaziendale.memento.GestoreSalvataggi;
import java.io.IOException;

public class ComandoSalva implements ComandoIF{

    private GestoreSalvataggi gestoreSalvataggi;
    private Organigramma organigramma;
    private String percorso;

    public ComandoSalva(GestoreSalvataggi gestoreSalvataggi, Organigramma organigramma, String percorso) {
        this.gestoreSalvataggi = gestoreSalvataggi;
        this.organigramma = organigramma;
        this.percorso = percorso;
    }

    @Override
    public void esegui() {
        try {
            gestoreSalvataggi.salvaSuFile(percorso, organigramma);
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }

    @Override
    public void annulla() {
        throw new UnsupportedOperationException("Impossibile annullare un salvataggio");
    }
}