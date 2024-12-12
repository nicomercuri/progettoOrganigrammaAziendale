package com.example.progettoorganigrammaaziendale.GUI;

import com.example.progettoorganigrammaaziendale.composite.NodoComposito;
import com.example.progettoorganigrammaaziendale.composite.Ruolo;

import javax.swing.*;

public class MenuContestuale extends JPopupMenu {

    //voglio fare che se clicco col tasto destro su un nodo mi appaiono delle azioni (es: aggiungi ruolo, ecc.)

    public MenuContestuale(NodoComposito nodoSelezionato) {

        //lista ruoli
        JMenuItem listaRuoli = new JMenuItem("Lista Ruoli");
        listaRuoli.addActionListener(e -> ruoliNodoSelezionato(nodoSelezionato));
        add(listaRuoli);

        //aggiungi ruolo
        //rimuovi ruolo
        //dipendenti di un nodo
        JMenuItem listaDipendenti = new JMenuItem("Lista Dipendenti");
        listaDipendenti.addActionListener(e -> dipendentiNodoSelezionato(nodoSelezionato));
        add(listaRuoli);

        //aggiungi dipendente
        //rimuovi dipendente

    }

    private void ruoliNodoSelezionato(NodoComposito nodoSelezionato) {
        StringBuilder ruoli = new StringBuilder("Ruoli presenti in " + nodoSelezionato.getNome() + ":\n");
        for(Ruolo ruolo : nodoSelezionato.getRuoli())
            ruoli.append("- ").append(ruolo.getNomeRuolo()).append("\n");
        JOptionPane.showMessageDialog(this, ruoli.toString(), "Lista Ruoli", JOptionPane.INFORMATION_MESSAGE);
    }

    private void dipendentiNodoSelezionato(NodoComposito nodoSelezionato) {

    }

}
