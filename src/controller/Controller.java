package controller;


import model.*;
import network.Server;
import view.MainView;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;

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
                try {
                    ServerGrid model = new ServerGrid();
                    // en aquest cas no usem controlador
                    // creem el servidor i establim relacions
                    GameController c = new GameController(model);
                    server = new Server(c, model, mainView, this);
                    server.startService();
                    ConectorDB conn = new ConectorDB(parser.getUser(), parser.getPassword(), parser.getDatabase(), parser.getPort(), parser.getDirectionIP());
                    gestorDB = new GestorDB(conn);
                    if (conn.connect()) {
                        System.out.println("DOPAISJDFOASDIJFASJPDI");
                        mainView.getMenuView().enableButtons();
                        mainView.showDialog("Conexio", 4);
                        mainView.getConfigView().changeTextField();
                        actualLayout = 1;
                        mainView.changePanel(actualLayout.toString());
                        //Me falta la parte del thread del server
                        serverEnabled = true;
                    } else {
                        mainView.showDialog("La conexio a la base de dades no es troba operativa", 10);
                    }
                } catch (Exception e1) {
                    mainView.showDialog("El port no es troba disponible", 10);
                }

            }

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
            actualLayout = 2;
            mainView.changePanel(actualLayout.toString());

            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }
        if(e.getActionCommand().equals("Exit")){
            System.out.println("entra");
            switchAndChange();
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }
        if(e.getActionCommand().equals("Ranking")){
            System.out.println("faltaaaaaa vistaaaaaa");
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }
        if(e.getActionCommand().equals("Registra")){
            actualLayout = 4;
            mainView.changePanel(actualLayout.toString());
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }

        if(e.getActionCommand().equals("Grafic")){

            actualLayout = 3;
            LinkedList<User> l = gestorDB.selectAllUsers();
            LinkedList<Score> score = new LinkedList<>();
            mainView.getGraphicView().setGra(score, 0);

            mainView.getGraphicView().setJcbUsers(l);
            System.out.println("estic dintre·····3");
            //actualitzaGraphic();


            mainView.changePanel(actualLayout.toString());
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }
        if(e.getActionCommand().equals("Connexion")){
            actualLayout = 2;
            mainView.changePanel(actualLayout.toString());
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }
        if(e.getActionCommand().equals("Elimina")){
            actualLayout = 5;
            LinkedList<User> l = gestorDB.selectAllUsers();
            changeTable(l);
            mainView.changePanel(actualLayout.toString());

            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }
        if(e.getActionCommand().equals("Delete")){

            System.out.println("eliminar");
            if(mainView.getDeleteView().getTable().getSelectedRow()!= -1) {
                LinkedList<User> l = gestorDB.selectAllUsers();
                User user = new User();
                user.setNickname(l.get(mainView.getDeleteView().getTable().getSelectedRow()).getNickname());
                mainView.getDeleteView().deleteUser();
                gestorDB.deleteUser(user);
                ((JButton) e.getSource()).getTopLevelAncestor().requestFocus();
            }
        }
        if(e.getActionCommand().equals("ExitDelete")){
            actualLayout = 1;
            mainView.changePanel(actualLayout.toString());
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();


        }
        if(e.getActionCommand().equals("ExitGraphic")){

            actualLayout = 1;
            mainView.changePanel(actualLayout.toString());
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }
        if(e.getActionCommand().equals("Change_user_userMode")){
            //LinkedList<User> l = gestorDB.selectAllUsers();
            //mainView.getGraphicView().setJcbUsers(l);
            actualitzaGraphic();
            //LinkedList<User> l = gestorDB.selectAllUsers();
            //mainView.getGraphicView().setJcbUsers(l);
           // ((JComboBox)e.getSource()).getTopLevelAncestor().requestFocus();
            //mainView.getGraphicView().getJcbMode().getTopLevelAncestor().requestFocus();
        }
        if(e.getActionCommand().equals("Change_user_Player")){
            //LinkedList<User> l = gestorDB.selectAllUsers();
            //mainView.getGraphicView().setJcbUsers(l);
            actualitzaGraphic();
            //LinkedList<User> l = gestorDB.selectAllUsers();
            //mainView.getGraphicView().setJcbUsers(l);
            //((JComboBox)e.getSource()).getTopLevelAncestor().requestFocus();
            //mainView.getGraphicView().getJcbUsers().getTopLevelAncestor().requestFocus();
        }



    }

    private void actualitzaGraphic() {

        LinkedList<User> l = new LinkedList<>();
        l = gestorDB.selectAllUsers();
        LinkedList<Score> score = new  LinkedList<>();
        System.out.println(mainView.getGraphicView().getJcbUsers().getSelectedIndex()+" indice");
        if(mainView.getGraphicView().getJcbUsers().getSelectedIndex() != -1) {


            int i = 0;
            score = l.get(mainView.getGraphicView().getJcbUsers().getSelectedIndex()).getScore();

            System.out.println(score.size() + "dfgh");

            if (score != null) {

                if (mainView.getGraphicView().getJcbMode().getSelectedItem().equals("2 Players")) {
                    i = 1;
                } else {
                    if (mainView.getGraphicView().getJcbMode().getSelectedItem().equals("4 Players")) {
                        i = 2;
                    } else {
                        i = 3;
                    }
                }

                mainView.getGraphicView().setGra(score, i);
            } else {
                mainView.getGraphicView().setGra(score, 0);
            }
            //score.remove();
        }else{
            mainView.getGraphicView().setGra(score, 0);
        }
    }

    private void changeTable(LinkedList<User> l) {


       Object [][] data =  new Object[l.size()][7];

       for (int i  = 0 ; i< l.size(); i++){
           data [i][0] = "Delete";
           data [i][1] = l.get(i).getNickname();
           data [i][2] = l.get(i).recountType(1);
           data [i][3] = l.get(i).recountType(2);
           data [i][4] = l.get(i).recountType(3);
           data [i][5] = l.get(i).getDateRegister();
           data [i][6] = l.get(i).getDateAccess();

       }
       Object [] title = new Object[]{"", "Nikname", "Points x2", "Points x4", "Tournament", "Register", "Modification"};
       mainView.getDeleteView().getDm().setDataVector(data,title);
       mainView.getDeleteView().getTable().getColumn("").setCellRenderer(mainView.getDeleteView().getBrDelte());
       mainView.getDeleteView().getTable().getColumn("").setCellEditor( mainView.getDeleteView().getBtDelete());




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
                if (mainView.showDialog("Si abandona la partida sera penalizado./n¿esta usted seguro de abandonar?", actualLayout)) {
                    actualLayout = 5;
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


    }

    public boolean isServerEnabled() {
        return serverEnabled;
    }

    public void setServerEnabled(boolean serverEnabled) {
        this.serverEnabled = serverEnabled;
    }
}
