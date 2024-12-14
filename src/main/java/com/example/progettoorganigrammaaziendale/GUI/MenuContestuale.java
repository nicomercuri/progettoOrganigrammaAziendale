package com.example.progettoorganigrammaaziendale.GUI;

import com.example.progettoorganigrammaaziendale.command.*;
import com.example.progettoorganigrammaaziendale.composite.Dipendente;
import com.example.progettoorganigrammaaziendale.composite.NodoComposito;
import com.example.progettoorganigrammaaziendale.composite.Ruolo;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuContestuale extends JPopupMenu {

    //voglio fare che se clicco col tasto destro su un nodo mi appaiono delle azioni (es: aggiungi ruolo, ecc.)

    public MenuContestuale(NodoComposito nodoSelezionato, GestoreComandi gestoreComandi) {

        JMenuItem listaRuoli = new JMenuItem("Lista Ruoli");
        listaRuoli.addActionListener(e -> ruoliNodoSelezionato(nodoSelezionato));
        add(listaRuoli);

        JMenuItem aggiungiRuolo = new JMenuItem("Aggiungi Ruolo");
        aggiungiRuolo.addActionListener(e -> aggiungiRuolo(nodoSelezionato, gestoreComandi));
        add(aggiungiRuolo);

        JMenuItem rimuoviRuolo = new JMenuItem("Rimuovi Ruolo");
        rimuoviRuolo.addActionListener(e -> rimuoviRuolo(nodoSelezionato, gestoreComandi));
        add(rimuoviRuolo);

        JMenuItem listaDipendenti = new JMenuItem("Lista Dipendenti");
        listaDipendenti.addActionListener(e -> dipendentiNodoSelezionato(nodoSelezionato));
        add(listaDipendenti);

        JMenuItem aggiungiDipendente = new JMenuItem("Aggiungi Dipendente");
        aggiungiDipendente.addActionListener(e -> aggiungiDipendente(nodoSelezionato,gestoreComandi));
        add(aggiungiDipendente);

        JMenuItem rimuoviDipendente = new JMenuItem("Rimuovi Dipendente");
        rimuoviDipendente.addActionListener(e -> rimuoviDipendente(nodoSelezionato, gestoreComandi));
        add(rimuoviDipendente);

    }


    //SISTEMARE: nel menu a tendina dei dipendenti presenti mi appare anche il ruolo dato che il
    //toString di Dipendente ha anche il ruolo
    private void rimuoviDipendente(NodoComposito nodoSelezionato, GestoreComandi gestoreComandi) {
        List<Dipendente> dipendentiDisponibili = nodoSelezionato.getDipendenti();
        if (dipendentiDisponibili.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Non ci sono dipendenti da rimuovere!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Dipendente dipendenteDaRimuovere = (Dipendente) JOptionPane.showInputDialog(
                this,
                "Seleziona un dipendente da rimuovere:",
                "Rimuovi Dipendente",
                JOptionPane.PLAIN_MESSAGE,
                null,
                dipendentiDisponibili.toArray(),
                null
        );
        if (dipendenteDaRimuovere != null) {
            gestoreComandi.eseguiComando(new ComandoRimuoviDipendente(nodoSelezionato, dipendenteDaRimuovere));
        }
    }


    //sistemare: migliorare dimensioni dialog
    private void aggiungiDipendente(NodoComposito nodoSelezionato, GestoreComandi gestoreComandi) {
        JDialog dialog = new JDialog((Frame) null, "Aggiungi Dipendente", true);
        dialog.setLayout(new GridLayout(4,2,10,3));
        dialog.setSize(300,300);
        dialog.setLocationRelativeTo(null);

        JLabel labelNomeDipendente = new JLabel("Nome:");
        JTextField textFieldNomeDipendente = new JTextField();
        JLabel labelCognomeDipendente = new JLabel("Cognome:");
        JTextField textFieldCognomeDipendente = new JTextField();

        JLabel labelRuoloDipendente = new JLabel("Ruolo:");
        JComboBox<String> comboRuoli =  new JComboBox<>();
        for(Ruolo ruolo: nodoSelezionato.getRuoli()) {
            comboRuoli.addItem(ruolo.getNomeRuolo());
        }

        JButton btnConferma = new JButton("Conferma");
        JButton btnAnnulla = new JButton("Annulla");

        dialog.add(labelNomeDipendente);
        dialog.add(textFieldNomeDipendente);
        dialog.add(labelCognomeDipendente);
        dialog.add(textFieldCognomeDipendente);
        dialog.add(labelRuoloDipendente);
        dialog.add(comboRuoli);
        dialog.add(btnConferma);
        dialog.add(btnAnnulla);

        btnConferma.addActionListener(e -> {
            String nome = textFieldNomeDipendente.getText().trim();
            String cognome = textFieldCognomeDipendente.getText().trim();
            String ruolo = (String) comboRuoli.getSelectedItem();

            if (!nome.isBlank() && !cognome.isBlank() && ruolo != null) {
                Dipendente nuovoDipendente = new Dipendente(nome, cognome, ruolo);
                gestoreComandi.eseguiComando(new ComandoAggiungiDipendente(nodoSelezionato, nuovoDipendente));
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Tutti i campi devono essere compilati.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAnnulla.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void rimuoviRuolo(NodoComposito nodoSelezionato, GestoreComandi gestoreComandi) {
        List<Ruolo> ruoliDisponibili = nodoSelezionato.getRuoli();
        if (ruoliDisponibili.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Non ci sono ruoli da rimuovere!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Ruolo ruoloDaRimuovere = (Ruolo) JOptionPane.showInputDialog(
                this,
                "Seleziona un ruolo da rimuovere:",
                "Rimuovi Ruolo",
                JOptionPane.PLAIN_MESSAGE,
                null,
                ruoliDisponibili.toArray(),
                null
        );
        if (ruoloDaRimuovere != null) {
            gestoreComandi.eseguiComando(new ComandoRimuoviRuolo(nodoSelezionato, ruoloDaRimuovere));
        }
    }

    private void aggiungiRuolo(NodoComposito nodoSelezionato, GestoreComandi gestoreComandi) {
        String nomeRuolo = JOptionPane.showInputDialog(this, "Inserisci il ruolo:", "Aggiungi Ruolo", JOptionPane.PLAIN_MESSAGE);
        if(nomeRuolo != null && !nomeRuolo.isBlank()) {
            Ruolo ruolo = new Ruolo(nomeRuolo);
            gestoreComandi.eseguiComando(new ComandoAggiungiRuolo(nodoSelezionato, ruolo));
        }
    }

    private void ruoliNodoSelezionato(NodoComposito nodoSelezionato) {
        StringBuilder ruoli = new StringBuilder("Ruoli presenti in " + nodoSelezionato.getNome() + ":\n");
        for(Ruolo ruolo : nodoSelezionato.getRuoli())
            ruoli.append("- ").append(ruolo.getNomeRuolo()).append("\n");
        JOptionPane.showMessageDialog(this, ruoli.toString(), "Lista Ruoli", JOptionPane.INFORMATION_MESSAGE);
    }

    private void dipendentiNodoSelezionato(NodoComposito nodoSelezionato) {
        StringBuilder dipendenti = new StringBuilder("Dipendenti di " + nodoSelezionato.getNome() + ":\n");
        for (Dipendente dipendente : nodoSelezionato.getDipendenti()) {
            dipendenti.append(dipendente.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, dipendenti.toString(), "Lista Dipendenti", JOptionPane.INFORMATION_MESSAGE);
    }

}
