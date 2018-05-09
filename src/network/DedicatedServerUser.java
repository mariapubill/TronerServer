package network;


import controller.GameController;
import controller.UpdateClientThread;
import model.ServerGrid;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;


public class DedicatedServerUser extends Thread{
    private Socket sClient;
    private ObjectInputStream diStreamO;
    private ObjectOutputStream doStreamO;
    private boolean isOn = false;
    private ServerGrid model;
    private LinkedList<DedicatedServerUser> lClients;
    private Server server;
   // private AddMovementThread addMov;
    private GameController c;
  //  private Petition p;
  //  private Petition newPetition;
  //  private AddMovementThread itThread;
   // private int color;
    private UpdateClientThread itUpdateThread;
    private User user;
    private String usernamee;

    public DedicatedServerUser(Socket sClient, LinkedList<DedicatedServerUser> clients, Server server, GameController c) {
        this.sClient = sClient;
        this.lClients = clients;
        this.server = server;
        this.c = c;
       // this.newPetition = new Petition(39);
        int numClinent = clients.size();
        //this.itThread = new AddMovementThread(c, this, model, model.getPosicions(numClinent));
       // this.color = lClients.size() + 1;
       // itThread.setNewGrid(newPetition, color);
        //muchClients(lClients);
        user = new User("nickame", "password","email","data register", "data acces");
    }

    public DedicatedServerUser(String username){
        this.usernamee = username;

    }

    public UpdateClientThread getItUpdateThread() {
        return itUpdateThread;
    }

    public void setItUpdateThread(UpdateClientThread itUpdateThread) {
        this.itUpdateThread = itUpdateThread;
    }

    /*private void muchClients(LinkedList<DedicatedServerUser> lClients) {
        System.out.println(lClients.size());
        if (lClients.size() == 2) {

            for (int i = 0; i < 2; i++) {
                lClients.get(i).getItThread().start();
            }
            //itThread.start();
           // this.itUpdateThread = new UpdateClientThread(this);
           // itUpdateThread.start();

        }
    }*/


    public void startDedicatedServerUser() {
        System.out.println("entra");
        this.isOn = true;
        this.start();
    }

    public void stopDedicatedServerUser() {
        this.isOn = false;
        this.interrupt();
    }

    public void run() {

        try {
            this.doStreamO = new ObjectOutputStream(this.sClient.getOutputStream());
            this.diStreamO = new ObjectInputStream(this.sClient.getInputStream());
            this.doStreamO.writeObject(this.user);
            System.out.println("estic escrivint objecte server "+user.getNickname());

            while (this.isOn) {

                User user2 = (User)diStreamO.readObject();
                System.out.println("llegiex al server: "+user2.getNickname());

                switch(user2.getNickname()){
                    case "login":
                        checkLogin();
                        break;
                }
                //newPetition = (Petition) diStreamO.readObject();
               // itThread.setNewGrid(newPetition, color);
                this.updateAllClients();

            }
        } catch (IOException var2) {
            this.stopDedicatedServerUser();
            this.lClients.remove(this);
            JOptionPane.showMessageDialog((Component) null, var2.getMessage(), "Error", 0);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    private ObjectOutputStream getOutChannel() {
        return this.doStreamO;
    }

    public void updateAllClients() {
        Iterator var2 = this.lClients.iterator();

        while (var2.hasNext()) {
            DedicatedServerUser dServer = (DedicatedServerUser) var2.next();
            ObjectOutputStream outStream = dServer.getOutChannel();

            try {
                outStream.reset();
                //outStream.writeObject(this.model);
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

    public void checkLogin(){

        try {
            User userLogin = (User)diStreamO.readObject();
            System.out.println("llegiex desde el server: "+userLogin.getNickname());


            User isOkay = new User("Ok", " "," ", " ", " ");
            this.doStreamO.writeObject(isOkay);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


}

