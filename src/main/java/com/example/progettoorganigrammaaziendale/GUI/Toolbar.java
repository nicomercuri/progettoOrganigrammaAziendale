package com.example.progettoorganigrammaaziendale.GUI;

import javax.swing.*;

public class Toolbar extends JToolBar {

    public Toolbar(FramePrincipale framePrincipale) {

        JButton btnAggiungiNodo = new JButton("Aggiungi Nodo");
        JButton btnRimuoviNodo = new JButton("Rimuovi Nodo");
        JButton btnRinominaNodo = new JButton("Rinomina Nodo");

        JButton btnUndo = new JButton("Undo");
        JButton btnRedo = new JButton("Redo");

        JButton btnSalva = new JButton("Salva");
        JButton btnCarica = new JButton("Carica");

        btnAggiungiNodo.addActionListener(e -> aggiungiNodo());
        btnRinominaNodo.addActionListener(e -> rinominaNodo());
        btnRimuoviNodo.addActionListener(e -> rimuoviNodo());

        //fare listener per salva e ripristina

        add(btnAggiungiNodo);
        add(btnRimuoviNodo);
        add(btnRinominaNodo);
        addSeparator();
        add(btnUndo);
        add(btnRedo);
        addSeparator();
        add(btnSalva);
        add(btnCarica);
    }

    private void aggiungiNodo() {}
    private void rinominaNodo() {}
    private void rimuoviNodo() {}
}
