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
        ultimoSalvataggio = organigramma.creaMemento();
    }

    public void caricaDaFile(String percorso, Organigramma organigramma) throws IOException, ClassNotFoundException {
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
        organigramma.ripristinaMemento(ultimoSalvataggio);
    }

}