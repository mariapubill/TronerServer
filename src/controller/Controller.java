package controller;


import model.ConectorDB;
import model.GestorDB;
import model.Parser;
import model.ServerGrid;
import network.Server;
import view.MainView;

import javax.swing.*;
import java.awt.event.*;

public class Controller implements ActionListener, KeyListener,FocusListener {
    private Parser parser;
    private Integer actualLayout;
    private MainView mainView;
    private boolean serverEnabled = false;
    private GestorDB gestorDB;
    private Server server;

    public Controller(MainView mainView,Parser parser) {
        this.parser = parser;
        this.mainView = mainView;
        actualLayout = 1;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(actualLayout);
        if(e.getActionCommand().equals("Start")) {
            if (serverEnabled) {
                mainView.showDialog("La connexio al servidor ja es troba feta", 3);
                mainView.getConfigView().changeTextField();
            }
            ((JButton) e.getSource()).getTopLevelAncestor().requestFocus();
            if (!serverEnabled) {
                System.out.println(parser.getComunicationPort());
                try{
                    ServerGrid model = new ServerGrid();
                    // en aquest cas no usem controlador
                    // creem el servidor i establim relacions
                    GameController c = new GameController(model);
                    server = new Server(c, model,mainView,this);
                    server.startService();
                    ConectorDB conn = new ConectorDB(parser.getUser(), parser.getPassword(), parser.getDatabase(), parser.getPort(), parser.getDirectionIP());
                    gestorDB = new GestorDB(conn);
                    if(conn.connect()) {
                        System.out.println("DOPAISJDFOASDIJFASJPDI");
                        mainView.getMenuView().enableButtons();
                        mainView.showDialog("Conexio", 4);
                        mainView.getConfigView().changeTextField();
                        actualLayout = 1;
                        mainView.changePanel(actualLayout.toString());
                        //Me falta la parte del thread del server
                        serverEnabled = true;
                    }else{
                        mainView.showDialog("La conexio a la base de dades no es troba operativa",10);
                    }
                }catch(Exception e1){
                    mainView.showDialog("El port no es troba disponible",10);
                }

            }
               /*
                if (!serverEnabled) {
                    serverEnabled = true;
                    mainView.getMenuView().enableButtons();
                    mainView.showDialog("Conexio",4);
                    mainView.getConfigView().changeTextField();
                    actualLayout = 1;
                    mainView.changePanel(actualLayout.toString());
            }*/

        }
        if(e.getActionCommand().equals("Stop")){
            mainView.getConfigView().changeTextField();
            if(serverEnabled) {
                serverEnabled = false;
                gestorDB.closeConnection();
                server.closeSocket();
                server.interrupt();
              //  gestorDB.cleanURL();
                mainView.getMenuView().disableButtons();
                ((JButton) e.getSource()).getTopLevelAncestor().requestFocus();
        //
                //        server.stopDedicatedServers();
                server.interrupt();
                mainView.showDialog("Conexio detinguda", 3);
            }else{
                mainView.showDialog("La conexio es troba detinguda", 3);
            }
        }
        if(e.getActionCommand().equals("Label")){

            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }
        if(e.getActionCommand().equals("Exit")){
            System.out.println("entra");
            switchAndChange();
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }
        if(e.getActionCommand().equals("Ranking")){
            System.out.println("lool");
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }
        if(e.getActionCommand().equals("Registra")){
            actualLayout = 4;
            mainView.changePanel(actualLayout.toString());
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }

        if(e.getActionCommand().equals("Grafic")){
            actualLayout = 3;
            mainView.changePanel(actualLayout.toString());
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }
        if(e.getActionCommand().equals("Connexion")){
            actualLayout = 2;
            mainView.changePanel(actualLayout.toString());
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }
        if(e.getActionCommand().equals("Elimina")){
            System.out.println("PENDIENTE DELETE");
         //   actualLayout = 2;
          //  mainView.changePanel(actualLayout.toString());
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }

        mainView.getGraphicView().getJcbMode().getTopLevelAncestor().requestFocus();
        mainView.getGraphicView().getJcbUsers().getTopLevelAncestor().requestFocus();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("fu");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 27) {
            switchAndChange();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void switchAndChange() {
        switch (actualLayout) {

            case 1:
                System.out.println("dkaoskdosap");
                if (mainView.showDialog("¿Desea cerrar el servidor?", actualLayout)) {
                    System.exit(0);
                }
                break;
            case 2:
                actualLayout = 1;
                mainView.changePanel(actualLayout.toString());
            case 3:
                actualLayout = 1;
                mainView.changePanel(actualLayout.toString());
                break;
            case 4:
                actualLayout = 1;
                mainView.changePanel(actualLayout.toString());
                break;
            case 5:
                if (mainView.showDialog("¿Desea volver a la pantalla de inicio?", actualLayout)) {
                    actualLayout = 1;
                    mainView.changePanel(actualLayout.toString());
                }
                break;
            case 7:
                if (mainView.showDialog("Si abandona la partida sera penalizado.\n¿esta usted seguro de abandonar?", actualLayout)) {
                    actualLayout = 5;
                    //AQUI HABRIA QUE RESTAR
                    mainView.changePanel(actualLayout.toString());
                }
            default:
                actualLayout--;
                mainView.changePanel(actualLayout.toString());
                break;
        }
    }


    @Override
    public void focusGained(FocusEvent e) {

        System.out.println("lo gana");
    }

    @Override
    public void focusLost(FocusEvent e) {

        System.out.println("adeu");

        //  System.out.println(e.getSource.(mainView.getConfigView().getTextField()));
        // mainView.getConfigView().getTextField().getTopLevelAncestor().requestFocus();
        // mainView.getConfigView().getTextField().setRequestFocusEnabled(true);
    }

    public boolean isServerEnabled() {
        return serverEnabled;
    }

    public void setServerEnabled(boolean serverEnabled) {
        this.serverEnabled = serverEnabled;
    }
}
