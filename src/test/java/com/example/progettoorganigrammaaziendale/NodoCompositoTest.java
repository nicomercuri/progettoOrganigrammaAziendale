package com.example.progettoorganigrammaaziendale;

import com.example.progettoorganigrammaaziendale.composite.Dipendente;
import com.example.progettoorganigrammaaziendale.composite.NodoComposito;
import com.example.progettoorganigrammaaziendale.composite.NodoIF;
import com.example.progettoorganigrammaaziendale.composite.Ruolo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;

public class NodoCompositoTest {

    private NodoComposito nodoRadice;
    private NodoComposito nodoPrimoFiglio;
    private NodoComposito nodoSecondoFiglio;
    private Ruolo ruolo;
    private Dipendente dipendente;

    @BeforeEach
    public void setUp() {
        nodoRadice = new NodoComposito("radice");
        nodoPrimoFiglio = new NodoComposito("primoFiglio");
        nodoSecondoFiglio = new NodoComposito("secondoFiglio");
        ruolo = new Ruolo("direttore");
        dipendente = new Dipendente("Nicola", "Mercuri");
    }

    @Test
    public void testAggiungiERimuoviFiglio() {
        nodoRadice.aggiungiNodo(nodoPrimoFiglio);
        nodoRadice.aggiungiNodo(nodoSecondoFiglio);

        List<NodoIF> figli = nodoRadice.getFigli();
        assertEquals(2, figli.size());
        assertTrue(figli.contains(nodoPrimoFiglio));
        assertTrue(figli.contains(nodoSecondoFiglio));

        nodoRadice.rimuoviNodo(nodoPrimoFiglio);
        figli = nodoRadice.getFigli();
        assertEquals(1, figli.size());
        assertFalse(figli.contains(nodoPrimoFiglio));
    }

    @Test
    public void testAggiungiERimuoviRuolo() {
        nodoRadice.aggiungiRuolo(ruolo);

        List<Ruolo> ruoli = nodoRadice.getRuoli();
        assertEquals(1, ruoli.size());
        assertTrue(ruoli.contains(ruolo));

        nodoRadice.rimuoviRuolo(ruolo);
        ruoli = nodoRadice.getRuoli();
        assertTrue(ruoli.isEmpty());
    }

    @Test
    public void testAggiungiERimuoviDipendente() {
        nodoRadice.aggiungiDipendente(dipendente, ruolo);

        Map<Dipendente, Ruolo> dipendenti = nodoRadice.getDipendenti();
        assertEquals(1, dipendenti.size());
        assertTrue(dipendenti.containsKey(dipendente));
        assertEquals(ruolo, dipendenti.get(dipendente));

        nodoRadice.rimuoviDipendente(dipendente, ruolo);
        dipendenti = nodoRadice.getDipendenti();
        assertTrue(dipendenti.isEmpty());
    }

    @Test
    public void testTrovaPadre() {
        nodoRadice.aggiungiNodo(nodoPrimoFiglio);
        nodoPrimoFiglio.aggiungiNodo(nodoSecondoFiglio);

        NodoComposito padre = nodoPrimoFiglio.trovaPadre(nodoSecondoFiglio);
        assertEquals(nodoPrimoFiglio, padre);

        padre = nodoRadice.trovaPadre(nodoPrimoFiglio);
        assertEquals(nodoRadice, padre);
    }

    @Test
    public void testGetNomeEImpostaNome() {
        assertEquals("radice", nodoRadice.getNome());
        nodoRadice.setNome("radiceNuova");
        assertEquals("radiceNuova", nodoRadice.getNome());
    }
}