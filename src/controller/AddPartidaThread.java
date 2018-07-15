package controller;

import model.Petition;
import model.ServerGrid;
import network.DedicatedServerUser;

import java.util.LinkedList;

public class AddPartidaThread extends Thread {
    private  LinkedList<DedicatedServerUser> list;
    private AddMovementThread twoThread;
    private ServerGrid serverGrid;
    private UpdateClientThread updateClientThread;



    public AddPartidaThread(LinkedList<DedicatedServerUser> lClients ) {
        this.serverGrid = new ServerGrid();
        //Petition petition = new Petition(39);
        this.updateClientThread = new UpdateClientThread(lClients);
        updateClientThread.start();
        if (lClients.size() == 2) {

            for (int i = 0; i <2; i++) {
                lClients.get(i).getTwoThread().AddMovement(lClients.get(i), this.serverGrid, this.serverGrid.getPosicions(i));
                lClients.get(i).getTwoThread().setNewGrid(lClients.get(i).getNewPetition(), lClients.indexOf(this));
                lClients.get(i).getTwoThread().start();

                //lClients.get(i).getItThread().start();//tiro laddMovement
            }
            /*lClients.get(1).getTwoThread().AddMovement(lClients.get(1), this.serverGrid, this.serverGrid.getPosicions(1));
            lClients.get(1).getTwoThread().start();
            lClients.get(1).getTwoThread().setNewGrid(lClients.get(1).getNewPetition(), lClients.indexOf(this));*/


            /*this.twoThread = new AddMovement(lClients.get(1), this.serverGrid, this.serverGrid.getPosicions(2));
            this.twoThread.start();
            this.itUpdateThread = new UpdateClientThread(this);*/


            //
        }


    }

    public LinkedList<DedicatedServerUser> getList() {
        return list;
    }
    public ServerGrid getServerGrid() {
        return serverGrid;
    }

    public void setServerGrid(ServerGrid serverGrid) {
        this.serverGrid = serverGrid;
    }

    public void setList(LinkedList<DedicatedServerUser> list) {
        this.list = list;
    }


}
