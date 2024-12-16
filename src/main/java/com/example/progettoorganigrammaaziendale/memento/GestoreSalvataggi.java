package com.example.progettoorganigrammaaziendale.memento;

import com.example.progettoorganigrammaaziendale.composite.*;
import java.io.*;

public class GestoreSalvataggi {

    private Memento ultimoSalvataggio;

    public void salvaSuFile (String percorso, Organigramma organigramma) throws FileNotFoundException {
        salvaStato(organigramma);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(percorso))) {
            oos.writeObject(ultimoSalvataggio);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void salvaStato(Organigramma organigramma) {
        ultimoSalvataggio = new Memento(copiaOrganigramma(organigramma.getNodoRadice()));
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

    public void caricaDaFile(String percorso, Organigramma organigramma) throws IOException, ClassNotFoundException {
        if (organigramma == null) {
            throw new IllegalArgumentException("Errore nel caricamento: organigramma non inizializzato");
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(percorso))) {
            ultimoSalvataggio = (Memento) ois.readObject();
            if (ultimoSalvataggio != null) {
                caricaStato(organigramma);
            } else {
                throw new IllegalStateException("Il file non contiene un salvataggio valido.");
            }
        }
    }

    private void caricaStato(Organigramma organigramma) {
        organigramma.reset();
        organigramma.setNodoRadice(copiaOrganigramma(ultimoSalvataggio.getNodoRadice()));
        //ricopiare la radice di ultimo salvataggio mi serve per isolarlo dall'organigramma
        //e quindi lavorare su una copia e non direttamente su ultimo salvataggio
    }

}