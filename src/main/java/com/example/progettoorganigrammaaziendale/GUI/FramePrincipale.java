package com.example.progettoorganigrammaaziendale.GUI;

import javax.swing.*;
import java.awt.*;

public class FramePrincipale extends JFrame {

    private final PannelloOrganigramma pannelloOrganigramma;


    public FramePrincipale() {
        super("Organigramma Aziendale");
        pannelloOrganigramma = new PannelloOrganigramma(); //da sistemare

        setLayout(new BorderLayout());
        add(new Toolbar(this), BorderLayout.NORTH); //qui ci metto i vari bottoni (aggiungi, salva, etc.)
        //add(new JScrollPane(pannelloOrganigramma), BorderLayout.CENTER); //questo gestirà l'organigramma

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); //centra la finestra
    }




}
