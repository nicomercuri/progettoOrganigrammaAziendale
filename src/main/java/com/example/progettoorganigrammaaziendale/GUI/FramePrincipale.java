package com.example.progettoorganigrammaaziendale.GUI;

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
        //organigramma = capire come inizializzarlo;
        gestoreComandi = new GestoreComandi();
        pannelloOrganigramma = new PannelloOrganigramma(); //da sistemare

        setLayout(new BorderLayout());
        add(new Toolbar(this), BorderLayout.NORTH); //qui ci metto i vari bottoni (aggiungi, salva, etc.)
        //add(new JScrollPane(pannelloOrganigramma), BorderLayout.CENTER); //questo gestirà l'organigramma

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); //centra la finestra
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
