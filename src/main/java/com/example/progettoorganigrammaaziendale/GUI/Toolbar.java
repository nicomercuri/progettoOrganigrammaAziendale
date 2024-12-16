package com.example.progettoorganigrammaaziendale.GUI;

import com.example.progettoorganigrammaaziendale.command.*;
import com.example.progettoorganigrammaaziendale.composite.NodoComposito;
import com.example.progettoorganigrammaaziendale.composite.Organigramma;

import javax.swing.*;
import java.util.Arrays;

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
        NodoComposito nodoPadre = selezionaNodo(framePrincipale, "Seleziona il nodo padre");
        if (nodoPadre != null) {
            String nomeNodoNuovo = JOptionPane.showInputDialog(framePrincipale, "Inserisci il nome del nuovo nodo");
            String[] nomiDisponibili = framePrincipale.getOrganigramma().getNomiNodiPresenti();
            if (nomeNodoNuovo != null && !nomeNodoNuovo.isBlank()) {
                boolean nomeGiaPresente = Arrays.asList(nomiDisponibili).contains(nomeNodoNuovo);
                if (nomeGiaPresente) {
                    JOptionPane.showMessageDialog(framePrincipale,
                            "È già presente un nodo con questo nome.",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    gestoreComandi.eseguiComando(new ComandoAggiungiNodo(nodoPadre, nomeNodoNuovo));
                    framePrincipale.getPannelloOrganigramma().repaint();
                }
            }
        }
    }

    private NodoComposito selezionaNodo(FramePrincipale framePrincipale, String messaggio) {
        String[] nomiDisponibili = framePrincipale.getOrganigramma().getNomiNodiPresenti();
        if(nomiDisponibili.length == 0){
            JOptionPane.showMessageDialog(framePrincipale, "Non vi sono nodi disponibili","Errore", JOptionPane.ERROR_MESSAGE );
            return null;
        }
        String scelta = (String) JOptionPane.showInputDialog(framePrincipale,messaggio, "Seleziona Nodo",
                JOptionPane.QUESTION_MESSAGE, null, nomiDisponibili, nomiDisponibili[0]);
        return scelta != null ? framePrincipale.getOrganigramma().restituisciNodoSelezionato(scelta) : null;
    }

    private void rinominaNodo(FramePrincipale framePrincipale) {
        GestoreComandi gestoreComandi = framePrincipale.getGestoreComandi();
        NodoComposito nodoDaRinominare = selezionaNodo(framePrincipale, "Seleziona il nodo da rinominare");
        if(nodoDaRinominare != null) {
            String nomeNuovo = JOptionPane.showInputDialog(framePrincipale, "Inserisci il nuovo nome del nodo:");
            if (nomeNuovo != null && !nomeNuovo.isBlank()) {
                gestoreComandi.eseguiComando(new ComandoRinominaNodo(nodoDaRinominare, nomeNuovo));
                framePrincipale.getPannelloOrganigramma().repaint();
            }
        }
    }

    private void rimuoviNodo(FramePrincipale framePrincipale) {
        GestoreComandi gestoreComandi = framePrincipale.getGestoreComandi();
        NodoComposito nodoDaRimuovere = selezionaNodo(framePrincipale, "Seleziona il nodo da rimuovere");
        Organigramma organigramma = framePrincipale.getOrganigramma();
        if (nodoDaRimuovere != null) {
            int conferma = JOptionPane.YES_OPTION;
            if (!nodoDaRimuovere.getFigli().isEmpty()) {
                conferma = JOptionPane.showConfirmDialog(
                        framePrincipale,
                        "ATTENZIONE! Eliminando questo nodo elimini anche i figli. Vuoi procedere con l'eliminazione?",
                        "Conferma Rimozione",
                        JOptionPane.YES_NO_OPTION
                );
            }
            if (conferma == JOptionPane.YES_OPTION) {
                NodoComposito nodoPadre = organigramma.trovaPadre(nodoDaRimuovere);
                if (nodoPadre == null && nodoDaRimuovere == organigramma.getNodoRadice()) {
                    JOptionPane.showMessageDialog(framePrincipale, "Impossibile rimuovere la radice.");
                } else {
                    gestoreComandi.eseguiComando(new ComandoRimuoviNodo(nodoPadre, nodoDaRimuovere));
                    framePrincipale.getPannelloOrganigramma().repaint();
                }
            }
        }
    }

}