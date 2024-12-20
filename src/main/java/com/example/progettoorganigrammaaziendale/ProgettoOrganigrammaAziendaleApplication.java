package com.example.progettoorganigrammaaziendale;

import com.example.progettoorganigrammaaziendale.GUI.FramePrincipale;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class ProgettoOrganigrammaAziendaleApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FramePrincipale frame = new FramePrincipale();
            frame.setVisible(true);
        });
    }

}
