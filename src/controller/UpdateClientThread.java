package controller;



import network.DedicatedServerUser;

import java.util.LinkedList;

public class UpdateClientThread extends Thread {
    private boolean go;
    private LinkedList<DedicatedServerUser>  server;

    public UpdateClientThread(LinkedList<DedicatedServerUser> server) {
        this.go = false;
        this.server = server;

    }

    public void setGo(boolean go) {
        this.go = go;
    }

    public void run() {
        go = true;
        while (go) {
            try {
                Thread.sleep(100);
                server.get(0).updateAllClients(server);
                System.out.println("update Clients");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("surtoUpdate");
    }

}

