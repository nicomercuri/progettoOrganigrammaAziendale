package com.example.progettoorganigrammaaziendale.command;

import com.example.progettoorganigrammaaziendale.composite.NodoComposito;

public class ComandoRinominaNodo implements ComandoIF{

    private NodoComposito nodo;
    private String nomeNuovo;
    private String nomeVecchio; //per l'undo

    public ComandoRinominaNodo(NodoComposito nodo, String nomeNuovo) {
        this.nodo = nodo;
        this.nomeNuovo = nomeNuovo;
    }

    @Override
    public void esegui() {
        nomeVecchio = nodo.getNome();
        nodo.setNome(nomeNuovo);
    }

    @Override
    public void annulla() {
        nodo.setNome(nomeVecchio);
    }
}
