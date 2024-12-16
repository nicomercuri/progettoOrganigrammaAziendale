package com.example.progettoorganigrammaaziendale.GUI;

import com.example.progettoorganigrammaaziendale.command.GestoreComandi;
import com.example.progettoorganigrammaaziendale.composite.NodoComposito;
import com.example.progettoorganigrammaaziendale.composite.NodoIF;
import com.example.progettoorganigrammaaziendale.composite.Organigramma;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class PannelloOrganigramma extends JPanel {

    //qua ci gestisco tutta la parte grafica sui nodi dell'organigramma

    private GestoreComandi gestoreComandi;
    private Organigramma organigramma;
    private final Map<NodoComposito, Rectangle> posizioneNodi;

    public PannelloOrganigramma(GestoreComandi gestoreComandi, Organigramma organigramma) {
        this.gestoreComandi = gestoreComandi;
        this.organigramma = organigramma;
        this.posizioneNodi = new HashMap<>();

        setBackground(Color.WHITE);

        //cliccando col tasto destro su un nodo mi appare un menu
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    NodoComposito nodoSelezionato = trovaNodoSelezionato(e.getPoint());
                    if(nodoSelezionato != null)
                        mostraMenuContestuale(nodoSelezionato, e.getX(), e.getY());
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        posizioneNodi.clear();
        calcolaLarghezzaSottoalbero(organigramma.getNodoRadice());
        disegnaNodo(g2d, organigramma.getNodoRadice(), getWidth() / 2, 50);
    }

    private int calcolaLarghezzaSottoalbero(NodoComposito nodo) {
        int larghezzaNodo = 100;
        int margineFigli = 20;
        if (nodo.getFigli().isEmpty()) {
            nodo.setLarghezza(larghezzaNodo);
            return larghezzaNodo;
        }
        int larghezzaTotale = 0;
        for (NodoIF figlio : nodo.getFigli()) {
            NodoComposito figlioNodo = (NodoComposito) figlio;
            larghezzaTotale += calcolaLarghezzaSottoalbero(figlioNodo) + margineFigli;
        }
        larghezzaTotale -= margineFigli; //non mi serve il margine dell'ultimo nodo (quello tutto a destra)
        nodo.setLarghezza(Math.max(larghezzaNodo, larghezzaTotale));
        return nodo.getLarghezza();
    }

    private void disegnaNodo(Graphics2D g2d, NodoComposito nodo, int x, int y) {
        int larghezzaNodo = 100;
        int altezzaNodo = 50;
        int spazioVerticale = 100;

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(x - larghezzaNodo / 2, y, larghezzaNodo, altezzaNodo);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x - larghezzaNodo / 2, y, larghezzaNodo, altezzaNodo);

        posizioneNodi.put(nodo, new Rectangle(x - larghezzaNodo / 2, y, larghezzaNodo, altezzaNodo));

        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(nodo.getNome());
        int textX = x - textWidth / 2;
        int textY = y + (altezzaNodo / 2) + (fm.getAscent() / 2) - 2;
        g2d.drawString(nodo.getNome(), textX, textY);
        if (!nodo.getFigli().isEmpty()) {
            int startX = x - nodo.getLarghezza() / 2;
            int childY = y + altezzaNodo + spazioVerticale;
            for (NodoIF figlio : nodo.getFigli()) {
                NodoComposito figlioNodo = (NodoComposito) figlio;
                int childX = startX + figlioNodo.getLarghezza() / 2;
                g2d.drawLine(x, y + altezzaNodo, childX, childY);
                disegnaNodo(g2d, figlioNodo, childX, childY);
                startX += figlioNodo.getLarghezza() + 20;
            }
        }
    }

    private NodoComposito trovaNodoSelezionato(Point point) {
        for (Map.Entry<NodoComposito, Rectangle> entry : posizioneNodi.entrySet()) {
            if (entry.getValue().contains(point)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void mostraMenuContestuale(NodoComposito nodoSelezionato, int x, int y) {
        MenuContestuale menu = new MenuContestuale(nodoSelezionato,gestoreComandi);
        menu.show(this, x, y);
    }
}
