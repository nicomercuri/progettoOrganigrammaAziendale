package com.example.progettoorganigrammaaziendale;

import com.example.progettoorganigrammaaziendale.command.ComandoAggiungiNodo;
import com.example.progettoorganigrammaaziendale.command.GestoreComandi;
import com.example.progettoorganigrammaaziendale.composite.NodoComposito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestoreComandiTest {

    private GestoreComandi gestoreComandi;
    private NodoComposito radice;

    @BeforeEach
    public void setUp() {
        gestoreComandi = new GestoreComandi();
        radice = new NodoComposito("radice");
    }

    @Test
    public void testEseguiComandoAggiungiNodo() {
        String nomeFiglio = "figlio";
        ComandoAggiungiNodo comando = new ComandoAggiungiNodo(radice, nomeFiglio);
        gestoreComandi.eseguiComando(comando);
        assertEquals(1, radice.getFigli().size());
        assertEquals(nomeFiglio, radice.getFigli().get(0).getNome());
        assertEquals(1, gestoreComandi.sizeComandiEseguiti());
    }

    @Test
    public void testUndoAggiungiUnita() {
        String nomeFiglio = "figlio";
        ComandoAggiungiNodo comando = new ComandoAggiungiNodo(radice, nomeFiglio);
        gestoreComandi.eseguiComando(comando);
        gestoreComandi.undo();
        assertEquals(0, radice.getFigli().size());
        assertEquals(1, gestoreComandi.sizeComandiAnnullati());
    }

    @Test
    public void testRedoAggiungiUnita() {
        String nomeFiglio = "figlio";
        ComandoAggiungiNodo comando = new ComandoAggiungiNodo(radice, nomeFiglio);
        gestoreComandi.eseguiComando(comando);
        gestoreComandi.undo();
        gestoreComandi.redo();
        assertEquals(1, radice.getFigli().size());
        assertEquals(nomeFiglio, radice.getFigli().get(0).getNome());
        assertEquals(1, gestoreComandi.sizeComandiEseguiti());
    }

    @Test
    public void testUndoSenzaComandi() {
        gestoreComandi.undo();
        assertEquals(0, gestoreComandi.sizeComandiEseguiti());
        assertEquals(0, gestoreComandi.sizeComandiAnnullati());
    }

    @Test
    public void testRedoSenzaComandi() {
        gestoreComandi.redo();
        assertEquals(0, gestoreComandi.sizeComandiEseguiti());
        assertEquals(0, gestoreComandi.sizeComandiAnnullati());
    }
}