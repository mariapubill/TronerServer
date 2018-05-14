package view;


import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel {
    private JButton connexion;
    private JButton registraUsuari;
    private JButton eliminaUsuari;
    private JButton showRanking;
    private JButton showGrafic;
    private JButton exit;



    public MenuView(){
        this.setLayout(new GridLayout(6,1));
        connexion = new JButton("Connexio");
        registraUsuari = new JButton("Registra Usuari");
        eliminaUsuari = new JButton("Elimina Usuari");
        showGrafic = new JButton("Mostra Grafic");
        showRanking = new JButton("Mostra Ranking");
        exit = new JButton("Exit");

        registraUsuari.setEnabled(false);
        eliminaUsuari.setEnabled(false);
        showGrafic.setEnabled(false);
        showRanking.setEnabled(false);
        exit = new JButton("Exit");
        this.add(connexion);
        this.add(registraUsuari);
        this.add(eliminaUsuari);
        this.add(showRanking);
        this.add(showGrafic);
        this.add(exit);
    }
    public void registerControllerMenu(Controller c){

        connexion.addActionListener(c);
        connexion.setActionCommand("Connexion");
        registraUsuari.addActionListener(c);
        registraUsuari.setActionCommand("Registra");
        eliminaUsuari.addActionListener(c);
        eliminaUsuari.setActionCommand("Elimina");
        showRanking.addActionListener(c);
        showRanking.setActionCommand("Ranking");
        showGrafic.addActionListener(c);
        showGrafic.setActionCommand("Grafic");
        exit.addActionListener(c);
        exit.setActionCommand("Exit");

    }

    public void disableButtons() {
        registraUsuari.setEnabled(false);
        eliminaUsuari.setEnabled(false);
        showRanking.setEnabled(false);
        showGrafic.setEnabled(false);
    }

    public void enableButtons() {
        registraUsuari.setEnabled(true);
        eliminaUsuari.setEnabled(true);
        showRanking.setEnabled(true);
        showGrafic.setEnabled(true);
    }
}
