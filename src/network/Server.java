package network;


import controller.Controller;
import controller.GameController;
import model.Petition;
import model.ServerGrid;
import view.MainView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server extends Thread{
    private LinkedList<DedicatedServerUser> twoPlayer;
    private LinkedList<DedicatedServerUser> fourPlayer;
    private LinkedList<DedicatedServerUser> tournamentPlayer;


    private ServerSocket sSocket;
    private ServerSocket sSocketUser;

    private LinkedList <DedicatedServer> dServers;
    private LinkedList<DedicatedServerUser> dServersUsers;

    private static final int PORT_USER = 12345;
    private static final int PORT = 50000;

    // Relacio amb el fil dexecucio que escolta les peticions de connexio
    private DedicatedServer dServer;
    private DedicatedServerUser dServerUser;

    // Relacio amb el controlador per notificar les recepcions de missatges
    private GameController controller;
    private Controller controllerGeneral;
    private ServerGrid model;
    private boolean isOn;

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
            }catch (Exception e){
                foo = -1;
            }
            this.controller = controller;
            this.controllerGeneral = controllerGeneral;
            this.isOn = false;
            this.model = model;
            System.out.println(foo);
            // obrim un socket de tipus servidor
         //   this.sSocket = new ServerSocket(PORT);//Te lo da el admin
            this.sSocketUser = new ServerSocket(foo); //Te lo da el fichero
            this.sSocketUser.setReuseAddress(true);


            this.dServers = new LinkedList<DedicatedServer>();
            this.dServersUsers = new LinkedList<DedicatedServerUser>();

            this.twoPlayer = new LinkedList<DedicatedServerUser>();
            this.fourPlayer = new LinkedList<DedicatedServerUser>();
            this.tournamentPlayer= new LinkedList<DedicatedServerUser>();

        } catch (IOException e) {
            System.out.println("NO LO HACE");
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

    public void run()  {
        while (isOn) {
            try {
                System.out.println("louru");
                // acceptem peticions de connexio dels clients
                // BLOQUEJA EXECUCIO DEL THREAD
                //Socket sClient = sSocket.accept();
                Socket sClientUser =  sSocketUser.accept(); //mirar aqui no hace nada a partir de aqui
                System.out.println("Cagoendios");
                // creem un nou servidor dedicat per atendre les
                // peticions del client
                //DedicatedServer pwClient = new DedicatedServer(model, sClient, dServers, this, controller);

                DedicatedServerUser pwClient2 =  new DedicatedServerUser(sClientUser, dServersUsers, this, controller);
                System.out.println("koapspdokasdokpa");
                //dServers.add(pwClient);
                dServersUsers.add(pwClient2);

                // engegem el servidor dedicat
                //pwClient.startDedicatedServer();
                pwClient2.startDedicatedServerUser();

                while (!controllerGeneral.isServerEnabled()){ //No llega

                }
                stopDedicatedServers();
            } catch (IOException e) {
                System.out.println("liao");
                e.printStackTrace();
            }
        }

    }


    /**
     * envia la comanda rebuda al controlador pq la gestioni
     * @param newPetition
     */
    public void commandReceived(Petition newPetition) {
    }

    /**
     * finció que actualitza la informacio en els diferents clients
     */
    public void actualitzarClients(){
        dServers.get(0).updateAllClients();
    }

    public void stopDedicatedServers(){
        for(int i = 0; i <dServersUsers.size();i++){
            System.out.println("EUROVISION");
         //   dServersUsers.get(i).stopDedicatedServerUser();
            dServersUsers.get(i).interrupt();
        }
    }
}
