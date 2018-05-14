package network;


import controller.GameController;
import controller.UpdateClientThread;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
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
    private GameController c;
    private boolean stop;
    private GestorDB gestorDB;
    private ConectorDB conectorDB;
    private UpdateClientThread itUpdateThread;
    private User user;
    private String usernamee;
    private Parser parser;

    public DedicatedServerUser(Socket sClient, LinkedList<DedicatedServerUser> clients, Server server, GameController c) {
        this.sClient = sClient;
        this.lClients = clients;
        this.server = server;
        this.c = c;
        this.stop = false;
       // this.newPetition = new Petition(39);
        int numClinent = clients.size();
        //this.itThread = new AddMovementThread(c, this, model, model.getPosicions(numClinent));
       // this.color = lClients.size() + 1;
       // itThread.setNewGrid(newPetition, color);
        //muchClients(lClients);
        System.out.println("PRUEBA 3");
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
            this.doStreamO.writeObject(new String("hey im there"));
            parser = new Parser();
            System.out.println("estic al server");
            LocalDate localDate = LocalDate.now();
            parser.readJsonFile();
            ConectorDB conn = new ConectorDB(parser.getUser(), parser.getPassword(), parser.getDatabase(), parser.getPort(), parser.getDirectionIP());
            // ConectorDB conn = new ConectorDB(parser.getUser(), parser.getPassword(), parser.getDatabase(), parser.getPort(), parser.getDirectionIP());
            boolean connected = conn.connect();
            System.out.println(connected);
            gestorDB = new GestorDB(conn);
            if (connected) {
                // System.out.println("estic escrivint objecte server "+user.getNickname());

                while (this.isOn) {

                    String param = (String) diStreamO.readObject();
                    System.out.println("llegiex desde el server: " + param);
                    System.out.println(param);
                    switch (param) {
                        case "login":
                            checkLogin();
                            break;

                        case "sign":
                            checkSignIn();
                            break;

                        case "gameTwo":
                            System.out.println("estic entrant a game");
                            if( encua(2)){
                                sendReady(1);
                            }else{
                                sendReady(0);
                            }

                            break;
                        case "4game":


                            encua(4);
                            break;
                        case "Tournament":
                            encua(5);
                            break;
                    }
                    //newPetition = (Petition) diStreamO.readObject();
                    // itThread.setNewGrid(newPetition, color);
                    this.updateAllClients();

                }
            }
            } catch(IOException var2){
                this.stopDedicatedServerUser();
                this.lClients.remove(this);
                JOptionPane.showMessageDialog(null, var2.getMessage(), "Error", 0);
            } catch(ClassNotFoundException e){
                e.printStackTrace();
            }
    }

    private void sendReady(int ready) {

        try {
            doStreamO.writeObject(ready);
            System.out.println("encuat correctament");
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("he enviat al client ready"+ready);

    }

    private boolean encua(int type) {
        switch(type){

            case 2:
               server.getTwoPlayer().add(this);
               if(server.getTwoPlayer().size()==2){
                   return true;
               }else{
                   return false;
               }
            case 4:
                server.getFourPlayer().add(this);
                if(server.getTwoPlayer().size()==4){
                    return true;
                }else{
                    return false;
                }

            case 5:
                server.getTournamentPlayer().add(this);
                if(server.getTwoPlayer().size()==4){
                    return true;
                }else{
                    return false;
                }

                default:
                    System.out.println("ha entrat al default del switch");
                    return false;
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
        boolean isOkay = false;
        try {
            User userLogin = (User)diStreamO.readObject();
            System.out.println("llegiex desde el server: "+userLogin.getNickname());
            System.out.println(userLogin.getPassword());
            parser.hashMD5(userLogin);
            isOkay = gestorDB.logIn(userLogin);
            doStreamO.writeObject(isOkay);
            System.out.println("despres  e¡denviar isOkey");
            if(isOkay){
                doStreamO.writeObject(userLogin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void checkSignIn(){
        LocalDate localDate = LocalDate.now();
        boolean isOkay = false;
        try {
            User userSign = (User) diStreamO.readObject();
            System.out.println("llegiex desde el server: "+userSign.getNickname());
            userSign.setDateAccess(localDate.toString());
            userSign.setDateRegister(localDate.toString());
            System.out.println();
            if(gestorDB.registraUsuari(userSign,parser)){
                isOkay = true;
               System.out.println("Registro con exito");
             }else{
                System.out.println("Error de registro");
                  //devuelve un boleano
              }
            doStreamO.writeObject(isOkay);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}

