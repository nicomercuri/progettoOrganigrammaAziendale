package com.example.progettoorganigrammaaziendale.GUI;

import com.example.progettoorganigrammaaziendale.command.ComandoCarica;
import com.example.progettoorganigrammaaziendale.command.GestoreComandi;
import com.example.progettoorganigrammaaziendale.composite.Organigramma;
import com.example.progettoorganigrammaaziendale.memento.GestoreSalvataggi;
import javax.swing.*;
import java.awt.*;

public class FramePrincipale extends JFrame {

    private final PannelloOrganigramma pannelloOrganigramma;
    private final GestoreComandi gestoreComandi;
    private final GestoreSalvataggi gestoreSalvataggi;
    private final Organigramma organigramma;

    public FramePrincipale() {
        super("Organigramma Aziendale");
        gestoreSalvataggi = new GestoreSalvataggi();
        gestoreComandi = new GestoreComandi();
        organigramma = caricaOCreaOrganigramma();
        pannelloOrganigramma = new PannelloOrganigramma(gestoreComandi, organigramma);

        setLayout(new BorderLayout());
        add(new Toolbar(this), BorderLayout.NORTH); //qui ci metto i vari bottoni (aggiungi, salva, etc.)
        add(new JScrollPane(pannelloOrganigramma), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); //centra la finestra
    }

    private Organigramma caricaOCreaOrganigramma() {
        int scelta = JOptionPane.showOptionDialog(this,
                "Vuoi caricare un organigramma esistente o crearne uno nuovo?",
                "Carica o Nuovo Organigramma",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Carica", "Nuovo"},
                "Nuovo");
        if (scelta == JOptionPane.YES_OPTION) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String percorso = fileChooser.getSelectedFile().getAbsolutePath();
                Organigramma nuovoOrganigramma = new Organigramma("Radice Temporanea");
                try {
                    ComandoCarica caricaComando = new ComandoCarica(gestoreSalvataggi, nuovoOrganigramma, percorso);
                    gestoreComandi.eseguiComando(caricaComando);
                    return nuovoOrganigramma;
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Errore durante il caricamento: " + ex.getMessage(),
                            "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        String nomeRadice = chiediNomeRadice();
        return new Organigramma(nomeRadice);
    }

    private String chiediNomeRadice() {
        String nomeRadice = JOptionPane.showInputDialog(this, "Inserisci il nome del nodo radice:", "Nodo Radice", JOptionPane.PLAIN_MESSAGE);
        if (nomeRadice == null || nomeRadice.isBlank()) {
            nomeRadice = "Radice"; //se l'utente non inserisce il nome
        }
        return nomeRadice;
    }

    public PannelloOrganigramma getPannelloOrganigramma() {
        return pannelloOrganigramma;
    }

    public GestoreComandi getGestoreComandi() {
        return gestoreComandi;
    }

    public GestoreSalvataggi getGestoreSalvataggi() {
        return gestoreSalvataggi;
    }

    public Organigramma getOrganigramma() {
        return organigramma;
    }
}
