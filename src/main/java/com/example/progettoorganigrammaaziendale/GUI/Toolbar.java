package com.example.progettoorganigrammaaziendale.GUI;

import com.example.progettoorganigrammaaziendale.command.ComandoAggiungiNodo;
import com.example.progettoorganigrammaaziendale.command.ComandoCarica;
import com.example.progettoorganigrammaaziendale.command.ComandoSalva;
import com.example.progettoorganigrammaaziendale.command.GestoreComandi;
import com.example.progettoorganigrammaaziendale.composite.NodoComposito;
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

        btnAggiungiNodo.addActionListener(e -> aggiungiNodo(framePrincipale));
        btnRinominaNodo.addActionListener(e -> rinominaNodo(framePrincipale));
        btnRimuoviNodo.addActionListener(e -> rimuoviNodo(framePrincipale));

        btnUndo.addActionListener(e -> {
            framePrincipale.getGestoreComandi().undo();
            framePrincipale.getPannelloOrganigramma().repaint();
        });

        btnRedo.addActionListener(e -> {
            framePrincipale.getGestoreComandi().redo();
            framePrincipale.getPannelloOrganigramma().repaint();
        });

        btnSalva.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(framePrincipale) == JFileChooser.APPROVE_OPTION) {
                String percorso = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    framePrincipale.getGestoreComandi().eseguiComando(new ComandoSalva(framePrincipale.getGestoreSalvataggi(), framePrincipale.getOrganigramma(), percorso));
                    JOptionPane.showMessageDialog(framePrincipale, "Salvataggio completato con successo!");
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(framePrincipale, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCarica.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(framePrincipale) == JFileChooser.APPROVE_OPTION) {
                String percorso = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    framePrincipale.getGestoreComandi().eseguiComando(new ComandoCarica(framePrincipale.getGestoreSalvataggi(), framePrincipale.getOrganigramma(), percorso));
                    framePrincipale.getPannelloOrganigramma().repaint();
                    JOptionPane.showMessageDialog(null, "Caricamento completato con successo!");
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(framePrincipale, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

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

    private void aggiungiNodo(FramePrincipale framePrincipale) {
        GestoreComandi gestoreComandi = framePrincipale.getGestoreComandi();
        NodoComposito nodoPadre = selezionaNodoPadre(framePrincipale);
        String nomeNodoNuovo = JOptionPane.showInputDialog(framePrincipale, "Inserisci il nome del nuovo nodo");
        if(nomeNodoNuovo != null && !nomeNodoNuovo.isBlank()) {
            gestoreComandi.eseguiComando(new ComandoAggiungiNodo(nodoPadre, nomeNodoNuovo));
            framePrincipale.getPannelloOrganigramma().repaint();
        }
    }

    private NodoComposito selezionaNodoPadre(FramePrincipale frame) {
        String[] nomiDisponibili = frame.getOrganigramma().getNomiNodiPresenti();
        if(nomiDisponibili.length == 0){
            JOptionPane.showMessageDialog(frame, "Non vi sono nodi disponibili","Errore", JOptionPane.ERROR_MESSAGE );
            return null;
        }
        String scelta = (String) JOptionPane.showInputDialog(frame, "Seleziona il nodo padre", "Seleziona Nodo",
                JOptionPane.QUESTION_MESSAGE, null, nomiDisponibili, nomiDisponibili[0]);
        return scelta != null ? frame.getOrganigramma().restituisciNodoSelezionato(scelta) : null;
    }

    private void rinominaNodo() {}
    private void rimuoviNodo() {}


}
