package network;


import controller.*;
import model.*;
//import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;


public class DedicatedServerUser extends Thread {
    private Socket sClient;

    private TimerDo timerDo;
    private Total total;
    private LogIn logIn;
    private SingIn singIn;
    private Mode mode;
    private Key key;

    private boolean gameGas = false; //poso gameGas = true quan j ahagi fet el compte enderrera, llavors anire enviant serverGrid a tots els clients

    private ObjectInputStream diStreamO;

    private AddMovementThread twoThread;


    private AddPartidaThread addPartidaThread;

    private boolean game = false;
    private ObjectOutputStream doStreamO;
    //private boolean twoFull = false;
    private boolean fourFull = false;
    private boolean tournamentFull = false;
    private boolean isOn = false;
    private boolean gameIsOn = false;

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
    private Petition newPetition;


    public AddMovementThread getTwoThread() {
        return twoThread;
    }

    public DedicatedServerUser(Socket sClient, LinkedList<DedicatedServerUser> clients, Server server, GameController c) {



        this.sClient = sClient;
        this.lClients = clients;
        this.server = server;
        this.c = c;
        this.stop = false;
        timerDo = new TimerDo();
        this.newPetition = new Petition(39);

        user = new User("nickame", "password", "email", "data register", "data acces");
        //ERRRRRRRROOOOOOOOOOOOR!!!!
        //this.serverGrid = serverGrid;
        //this.serverGrid = new ServerGrid();
        this.twoThread = new AddMovementThread();
        //this.twoThread.setNewGrid(newPetition, 1);

    }

    public ObjectInputStream getDiStreamO() {
        return diStreamO;
    }

    public DedicatedServerUser(String username) {
        this.usernamee = username;

    }

    public UpdateClientThread getItUpdateThread() {
        return itUpdateThread;
    }

    public void setItUpdateThread(UpdateClientThread itUpdateThread) {
        this.itUpdateThread = itUpdateThread;
    }

    public Petition getNewPetition() {
        return newPetition;
    }

    public void setNewPetition(Petition newPetition) {
        this.newPetition = newPetition;
    }

    /*private void muchClients(LinkedList<DedicatedServerUser> lClients) {
        System.out.println(lClients.size());
        if (lClients.size() == 2) {

            for (int i = 0; i < 2; i++) {
                lClients.get(i).getItThread().start();
            }

            //twoThread.start();
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

            parser = new Parser();

            LocalDate localDate = LocalDate.now();
            parser.readJsonFile();
            ConectorDB conn = new ConectorDB(parser.getUser(), parser.getPassword(), parser.getDatabase(), parser.getPort(), parser.getDirectionIP());
            // ConectorDB conn = new ConectorDB(parser.getUser(), parser.getPassword(), parser.getDatabase(), parser.getPort(), parser.getDirectionIP());
            boolean connected = conn.connect();
            System.out.println(connected);
            gestorDB = new GestorDB(conn);





            if (connected) {
                while (this.isOn) {
                    total = (Total) diStreamO.readObject();
                    System.out.println(total + "-----------total");

                    if (total instanceof LogIn){
                        System.out.println("login");
                        checkLogin((LogIn)total);
                        System.out.println("123456789098765432345676543676545324345657764567867545364546354765876856545345676543424345676543435465765432456");
                    } else if (total instanceof SingIn){
                        System.out.println("singin");
                        checkSignIn((SingIn)total);
                    } else if (total instanceof Mode){

                        System.out.println("mode");
                        encua((Mode) total);



                    } else if (total instanceof Key) {
                        System.out.println("key");


                    }

                }








                /*while (this.isOn) {
                    String param = (String) diStreamO.readObject();
                    System.out.println(param+"<--param");
                    switch (param) {
                        case "login":
                            doStreamO.writeObject("login");
                            checkLogin();
                            break;

                        case "sign":
                            doStreamO.writeObject("singin");
                            checkSignIn();
                            break;

                        case "twoPlayer":
                            if (encua(2)){
                                timerDo.startCountDown(server.getTwoPlayer());
                            } else {
                                doStreamO.writeObject("notReady");
                            }
                            break;
                        case "4game":
                            if (encua(4)) {
                                timerDo.startCountDown(server.getFourPlayer());
                            } else {
                                doStreamO.writeObject("notReady");
                            }
                            break;
                        case "Tournament":
                            if (encua(4)) {
                                timerDo.startCountDown(server.getTournamentPlayer());
                            } else {
                                doStreamO.writeObject("notReady");
                            }
                        case "peticio":
                            //System.out.println("he entrat dins de peticio, ara em proposo posarme a llegir les newKeys(newPetition)");
                             readKey();

                            break;
                    }
                    //newPetition = (Petition) diStreamO.readObject();
                    // twoThread.setNewGrid(newPetition, color);

                }*/
            }
        } catch (IOException var2) {
            this.stopDedicatedServerUser();
            this.lClients.remove(this);
            System.out.println("ERROR AKI");
            JOptionPane.showMessageDialog(null, var2.getMessage(), "Error", 0);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }





    private void readKey() {
        System.out.println("entro a readKey");
        game = true;
        //while (game) {

            try {

                newPetition = (Petition) diStreamO.readObject();
                System.out.println(newPetition.getKey()+"<--KEYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            twoThread.setNewGrid(newPetition, lClients.indexOf(this)+1);
            try {
                System.out.println(server.getPartida().getServerGrid()+"");
                this.doStreamO.writeObject(server.getPartida().getServerGrid());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.updateAllClients(lClients);



        //}
    }


    private boolean recivedClientMove() {
        boolean recieved = false;

        try {
            recieved = (boolean) diStreamO.readObject();
            System.out.println("he rebut bolea " + recieved);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("he entrat al catch de recieved");
        }

        return recieved;
    }

    private void sendAllClientsIsReady(String string, LinkedList<DedicatedServerUser> dedicatedServerUser) {
        System.out.println("entro a update tothom isReady");
        Iterator var2 = dedicatedServerUser.iterator();

        while (var2.hasNext()) {
            DedicatedServerUser dServer = (DedicatedServerUser) var2.next();
            ObjectOutputStream outStream = dServer.getOutChannel();

            try {
                outStream.reset();
                outStream.writeObject(string);
            } catch (IOException var5) {
                JOptionPane.showMessageDialog((Component) null, var5.getMessage(), "Error, al mostrar als clients que estem READY", 0);
                var5.printStackTrace();
            }
        }
        System.out.println("crec que ja he enviat a tothom que estem ready, s'hauria de mostrar pantalla del joc");
    }


    private void sendReady(boolean ready) {

        try {
            doStreamO.writeObject(ready);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //retorna si la cua concreta està plena o encara no
    private boolean encua(Mode type) {
        System.out.println(type.getMode());
        switch (type.getMode()) {

            case 2:
                server.getTwoPlayer().add(this);
                timerDo.startCountDown(server.getTwoPlayer());
                if (server.getTwoPlayer().size() == 2) {
                    System.out.println("entra");
                    server.setTwoFull(true);
                    System.out.println("he entrat a encua 2 -----> muajajajajajajajajajajajajajajajajajajajajajajajajahahahhaajajjajajajajajajajajajajahahahahajajaah"+server.getTwoPlayer().size());
                    return true;
                } else {
                    return false;
                }
            case 4:
                server.getFourPlayer().add(this);
                timerDo.startCountDown(server.getFourPlayer());
                if (server.getTwoPlayer().size() == 4) {
                    fourFull = true;
                    return true;
                } else {
                    return false;
                }

            case 5:
                server.getTournamentPlayer().add(this);
                timerDo.startCountDown(server.getTournamentPlayer());
                if (server.getTwoPlayer().size() == 4) {
                    tournamentFull = true;
                    return true;
                } else {
                    return false;
                }

            default:
                return false;
        }

    }

    private ObjectOutputStream getOutChannel() {
        return this.doStreamO;
    }

    public void updateAllClients(LinkedList<DedicatedServerUser> lClients) {
        System.out.println("entro a update all clients --> envio serverGrid");
        Iterator var2 = lClients.iterator();

        while (var2.hasNext()) {
            System.out.println("                                                                                      quants putus cops entro");
            DedicatedServerUser dServer = (DedicatedServerUser) var2.next();
            ObjectOutputStream outStream = dServer.getOutChannel();

            try {
                outStream.reset();
                outStream.writeObject(server.getPartida().getServerGrid());
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

    public void checkLogin(LogIn userLogin) {
        boolean isOkay = false;
        try {

            //LogIn userLogin = (LogIn) diStreamO.readObject();
            System.out.println("hola");
            user.setNickname(userLogin.getNickName());
            user.setEmail("");
            user.setPassword(userLogin.getPassword());
            System.out.println(userLogin.getPassword());
            parser.hashMD5(user);
            isOkay = gestorDB.logIn(user);
            doStreamO.writeObject(isOkay);


        } catch (IOException e) {
            e.printStackTrace();
        }/*catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    public void checkSignIn(SingIn userLogin) {
        LocalDate localDate = LocalDate.now();
        boolean isOkay = false;
        try {
            //User userSign = (User) diStreamO.readObject();
            user.setNickname(userLogin.getNickName());
            user.setPassword(userLogin.getPassword());
            user.setDateAccess(localDate.toString());
            user.setDateRegister(localDate.toString());
            user.setEmail(userLogin.getEmail());
            System.out.println();
            if (gestorDB.registraUsuari(user, parser)) {
                isOkay = true;

            } else {

                //devuelve un boleano
            }
            doStreamO.writeObject(isOkay);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //envio qin layout toca posar a tots els clients
    public void enviaProgressCountDown(int counter, LinkedList<DedicatedServerUser> dedicatedServerUser) {
        String string;
        switch (counter) {
            case 0:
                sendAllClientsIsReady("zero",dedicatedServerUser);
                sendAllClientsIsReady("ready",dedicatedServerUser);
                sendAllClientsIsReady("letsGo",dedicatedServerUser);
                System.out.println("abans de tiro threads");
                tiroThreads();
                break;
            case 1:
                sendAllClientsIsReady("one",dedicatedServerUser);
                break;
            case 2:
                sendAllClientsIsReady("two",dedicatedServerUser);
                break;
            case 3:
                sendAllClientsIsReady("three",dedicatedServerUser);
                break;

        }

        /*System.out.println("entro a update tothom el layout"+counter);
        Iterator var2 = this.lClients.iterator();

        while (var2.hasNext()) {
            DedicatedServerUser dServer = (DedicatedServerUser) var2.next();
            ObjectOutputStream outStream = dServer.getOutChannel();

            try {
                outStream.reset();
                outStream.writeObject(counter);
            } catch (IOException var5) {
                //fJOptionPane.showMessageDialog((Component) null, var5.getMessage(), "Error, al mostrar als clients per qin numero del countdown anem puta vida TT", 0);
                var5.printStackTrace();
            }
        }*/
    }

    private void tiroThreads() {
        System.out.println(server.isTwoFull());
        if (server.isTwoFull()) {
            System.out.println("abans de much clients:)");
            server.muchClients(server.getTwoPlayer());
            System.out.println("despres de much clients");
            server.setTwoFull(false);
        }
        if (fourFull) {
            server.muchClients(server.getFourPlayer());

            fourFull = false;
        }
        if (tournamentFull) {
            server.muchClients(server.getTournamentPlayer());

            tournamentFull = false;
        }
    }



    public void stopThreads() {
        for (int i = 0; i == lClients.size(); i++) {
            lClients.get(i).getTwoThread().setGo(false);
            lClients.get(i).getTwoThread().interrupt();

        }
    }
}





