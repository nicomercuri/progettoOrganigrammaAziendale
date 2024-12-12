package com.example.progettoorganigrammaaziendale.GUI;

import com.example.progettoorganigrammaaziendale.composite.NodoComposito;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PannelloOrganigramma extends JPanel {

    //qua ci gestisco tutta la parte grafica sui nodi dell'organigramma

    public PannelloOrganigramma() {

        setBackground(Color.WHITE);

        //cliccando col tasto destro su un nodo mi appare un menu
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    NodoComposito nodoSelezionato = //capire come selezionarlo
                    if(nodoSelezionato != null)
                        mostraMenuContestuale(nodoSelezionato, e.getX(), e.getY());
                }
            }
        });
    }


    private void mostraMenuContestuale(NodoComposito nodoSelezionato, int x, int y) {
        MenuContestuale menu = new MenuContestuale(nodoSelezionato);
        menu.show(this, x, y);
    }
}
