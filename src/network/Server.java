package network;


import controller.AddMovementThread;
import controller.AddPartidaThread;
import controller.Controller;
import controller.GameController;
import model.Petition;
import model.ServerGrid;
import view.MainView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;

public class Server extends Thread {

    private LinkedList<DedicatedServerUser> twoPlayer;
    private LinkedList<DedicatedServerUser> fourPlayer;
    private LinkedList<DedicatedServerUser> tournamentPlayer;
    private boolean twoFull = false;

    public boolean isTwoFull() {
        return twoFull;
    }

    public void setTwoFull(boolean twoFull) {
        this.twoFull = twoFull;
    }







    private ServerSocket sSocketUser;
    //private ServerGrid serverGrid;


    private LinkedList<DedicatedServerUser> dServersUsers;

    private static final int PORT_USER = 12345;
   // private static final int PORT = 50000;

    // Relacio amb el fil dexecucio que escolta les peticions de connexio
   // private DedicatedServer dServer;
    private DedicatedServerUser dServerUser;

    // Relacio amb el controlador per notificar les recepcions de missatges
    private GameController controller;
    private Controller controllerGeneral;
    private ServerGrid model;
    private boolean isOn;
    private AddPartidaThread partida;
    private ServerGrid serverGrid;
    public LinkedList<DedicatedServerUser> getTwoPlayer() {
        return twoPlayer;
    }

    public void setTwoPlayer(LinkedList<DedicatedServerUser> twoPlayer) {
        this.twoPlayer = twoPlayer;
    }

    public LinkedList<DedicatedServerUser> getFourPlayer() {
        return fourPlayer;
    }

    public void setFourPlayer(LinkedList<DedicatedServerUser> fourPlayer) {
        this.fourPlayer = fourPlayer;
    }

    public LinkedList<DedicatedServerUser> getTournamentPlayer() {
        return tournamentPlayer;
    }

    public void setTournamentPlayer(LinkedList<DedicatedServerUser> tournamentPlayer) {
        tournamentPlayer = tournamentPlayer;
    }

    /**
     * Constructor amb paramentres.
     *
     * @param controller
     * @param model
     */


    public Server(GameController controller, ServerGrid model, MainView mainView, Controller controllerGeneral) {
        try {
            int foo = 0;
            String port = mainView.getConfigView().getTextField().getText();
            System.out.println(port);
            try {
                foo = Integer.parseInt(port);
            } catch (Exception e) {
                foo = -1;
            }
            //this.serverGrid = new ServerGrid();
            this.controller = controller;
            this.controllerGeneral = controllerGeneral;
            this.isOn = false;
            this.model = model;
            System.out.println(foo);
            // obrim un socket de tipus servidor
            //   this.sSocket = new ServerSocket(PORT);//Te lo da el admin
            this.sSocketUser = new ServerSocket(foo); //Te lo da el fichero
            System.out.println(foo);
            this.sSocketUser.setReuseAddress(true);



            this.dServersUsers = new LinkedList<DedicatedServerUser>();
            this.twoPlayer = new LinkedList<DedicatedServerUser>();
            this.fourPlayer = new LinkedList<DedicatedServerUser>();
            this.tournamentPlayer = new LinkedList<DedicatedServerUser>();

        } catch (IOException e) {

            //em.out.println(e.printStackTrace());
        }
    }


    /**
     * Inicia el servei per la recepcio de missatges
     */
    public void startService() {
        isOn = true;
        this.start();
    }

    public void run() {
        while (isOn) {
            try {

                Socket sClientUser = sSocketUser.accept(); //mirar aqui no hace nada a partir de aqui


                DedicatedServerUser pwClient2 = new DedicatedServerUser(sClientUser, dServersUsers, this, controller);
                dServersUsers.add(pwClient2);

                pwClient2.startDedicatedServerUser();


                // stopDedicatedServers();//////hola soc la blancaaa!!!, ho he comentat pq petava, pro crec q he fet mes mal q be
            } catch (IOException e) {
                System.out.println("catch del server del run");
                e.printStackTrace();
            }
        }

    }


    /**
     * envia la comanda rebuda al controlador pq la gestioni
     *
     * @param newPetition
     */
    public void commandReceived(Petition newPetition) {
    }

    /**
     * finció que actualitza la informacio en els diferents clients
     */


    public void stopDedicatedServers() {
        for (int i = 0; i < dServersUsers.size(); i++) {
            System.out.println("EUROVISION");
            //   dServersUsers.get(i).stopDedicatedServerUser();
            dServersUsers.get(i).interrupt();
            try {
                sSocketUser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }






    public AddPartidaThread getPartida() {
        return partida;
    }

    public void setPartida(AddPartidaThread partida) {
        this.partida = partida;
    }

    public void muchClients(LinkedList<DedicatedServerUser> lClients) {
            //ServerGrid serverGrid = new ServerGrid();
            System.out.println(lClients.size() +"<--size de la llista q estic tirant els threads");
            partida = new AddPartidaThread(lClients);
            partida.start();
            //twoPlayer.remove();
        /*if (lClients.size() == 2) {

            for (int i = 0; i < 2; i++) {
                    lClients.get(i).getTwoThread().start();
            }
            twoThread.start();
            this.itUpdateThread = new UpdateClientThread(this);

            itUpdateThread.start();

        }*/
        }
    }


