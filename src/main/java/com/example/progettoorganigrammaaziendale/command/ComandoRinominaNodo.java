package com.example.progettoorganigrammaaziendale.command;

import com.example.progettoorganigrammaaziendale.composite.NodoComposito;

public class ComandoRinominaNodo implements ComandoIF{

    //per il futuro, posso usare una lista per salvarmi i nomi nel caso facessi questa operazione più volte
    //(se nomino un nome NODO1, poi NODO2 e poi NODO3 con l'implementazione attuale non potrò tramite undo a tornare a NODO1)

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
