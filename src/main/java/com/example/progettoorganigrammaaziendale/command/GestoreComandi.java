package com.example.progettoorganigrammaaziendale.command;

import java.util.Stack;

public class GestoreComandi {

    private final Stack<ComandoIF> comandiEseguiti = new Stack<>();
    private final Stack<ComandoIF> comandiAnnullati = new Stack<>();

    public void eseguiComando(ComandoIF comando) {
        comando.esegui();
        comandiEseguiti.push(comando);
        comandiAnnullati.clear();
    }

    public void undo(){
        if(!comandiEseguiti.isEmpty()){
            ComandoIF comando = comandiEseguiti.pop();
            comando.annulla();
            comandiAnnullati.push(comando);
        }
    }

    public void redo(){
        if(!comandiAnnullati.isEmpty()){
            ComandoIF comando = comandiAnnullati.pop();
            comando.esegui();
            comandiEseguiti.push(comando);
        }
    }

}
