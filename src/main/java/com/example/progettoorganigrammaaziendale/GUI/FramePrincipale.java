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
        add(new Toolbar(this), BorderLayout.NORTH);
        add(new JScrollPane(pannelloOrganigramma), BorderLayout.CENTER); //esempio di decorator

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); //centra la finestra
    }

    private Organigramma caricaOCreaOrganigramma() {
        while (true) {
            int scelta = JOptionPane.showOptionDialog(this,
                    "Vuoi caricare un organigramma esistente da un file o crearne uno nuovo?",
                    "Avvio organigramma",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"Carica", "Nuovo"},
                    "Nuovo");
            if (scelta == JOptionPane.YES_OPTION) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String percorso = fileChooser.getSelectedFile().getAbsolutePath();
                    Organigramma nuovoOrganigramma = new Organigramma("Radice Temporanea");
                    //l'operazione di sopra mi serve solamente per avviare un organigramma con un nome reale in modo che
                    //poi venga sostituito dall'organigramma del file caricato
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
            } else if (scelta == JOptionPane.NO_OPTION) {
                String nomeRadice = chiediNomeRadice();
                return new Organigramma(nomeRadice);
            } else {
                System.exit(0);
            }
        }
    }

    private String chiediNomeRadice() {
        String nomeRadice = JOptionPane.showInputDialog(this,
                "Inserisci il nome del nodo radice:",
                "Nodo Radice",
                JOptionPane.PLAIN_MESSAGE);
        if (nomeRadice == null)
            System.exit(0);
        if (nomeRadice.isBlank())
            nomeRadice = "Radice";
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