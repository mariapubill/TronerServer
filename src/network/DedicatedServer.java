package network;




import controller.AddMovementThread;
import controller.GameController;
import controller.UpdateClientThread;
import model.Petition;
import model.ServerGrid;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;

public class DedicatedServer extends Thread {
    private Socket sClient;
    private ObjectInputStream diStreamO;
    private ObjectOutputStream doStreamO;
    private boolean isOn = false;
    private ServerGrid model;
    private LinkedList<DedicatedServer> lClients;
    private Server server;
    private AddMovementThread addMov;
    private GameController c;
    private Petition p;
    private Petition newPetition;
    private AddMovementThread itThread;
    private int color;
    private UpdateClientThread itUpdateThread;

    public DedicatedServer(ServerGrid model, Socket sClient, LinkedList<DedicatedServer> clients, Server server, GameController c) {
        this.model = model;
        this.sClient = sClient;
        this.lClients = clients;
        this.server = server;
        this.c = c;
        this.newPetition = new Petition(39);
        int numClinent = clients.size();
        this.itThread = new AddMovementThread(c, this, model, model.getPosicions(numClinent));
        this.color = lClients.size() + 1;
        itThread.setNewGrid(newPetition, color);
        muchClients(lClients);




    }

    public UpdateClientThread getItUpdateThread() {
        return itUpdateThread;
    }

    public void setItUpdateThread(UpdateClientThread itUpdateThread) {
        this.itUpdateThread = itUpdateThread;
    }

    private void muchClients(LinkedList<DedicatedServer> lClients) {
        System.out.println(lClients.size());
        if (lClients.size() == 2) {

            for (int i = 0; i < 2; i++) {
                lClients.get(i).getItThread().start();
            }
            itThread.start();
            this.itUpdateThread = new UpdateClientThread(this);
            itUpdateThread.start();

        }
    }


    public void startDedicatedServer() {
        this.isOn = true;
        this.start();
    }

    public void stopDedicatedServer() {
        this.isOn = false;
        this.interrupt();
    }

    public void run() {

        try {
            System.out.println("entra here");
            this.diStreamO = new ObjectInputStream(this.sClient.getInputStream());
            this.doStreamO = new ObjectOutputStream(this.sClient.getOutputStream());
            this.doStreamO.writeObject(this.model);

            while (this.isOn) {

                newPetition = (Petition) diStreamO.readObject();
                itThread.setNewGrid(newPetition, color);
                this.doStreamO.writeObject(model);
                this.updateAllClients();

            }
        } catch (IOException var2) {
            this.stopDedicatedServer();
            this.lClients.remove(this);
            JOptionPane.showMessageDialog((Component) null, var2.getMessage(), "Error", 0);
        } catch (ClassNotFoundException var3) {
            JOptionPane.showMessageDialog((Component) null, var3.getMessage(), "Error", 0);
            var3.printStackTrace();
        }

    }


    private ObjectOutputStream getOutChannel() {
        return this.doStreamO;
    }

    public void updateAllClients() {
        Iterator var2 = this.lClients.iterator();

        while (var2.hasNext()) {
            DedicatedServer dServer = (DedicatedServer) var2.next();
            ObjectOutputStream outStream = dServer.getOutChannel();

            try {
                outStream.reset();
                outStream.writeObject(this.model);
            } catch (IOException var5) {
                JOptionPane.showMessageDialog((Component) null, var5.getMessage(), "Error", 0);
                var5.printStackTrace();
            }
        }

    }

    public ObjectOutputStream getDoStreamO() {
        return doStreamO;
    }

    public ObjectInputStream getdiStreamO() {
        return diStreamO;
    }


    public AddMovementThread getItThread() {
        return itThread;
    }

    public void stopThreads() {



        for (int i = 0; i == lClients.size(); i++) {
            lClients.get(i).getItThread().setGo(false);
            lClients.get(i).getItThread().interrupt();

        }
        lClients.getLast().getItUpdateThread().setGo(false);
        lClients.getLast().getItUpdateThread().stop();


    }
}

