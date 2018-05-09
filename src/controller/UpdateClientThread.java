package controller;


import network.DedicatedServer;

public class UpdateClientThread extends Thread {
    private boolean go;
    private DedicatedServer server;

    public UpdateClientThread( DedicatedServer server) {
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
//                server.updateAllClients();
                System.out.println("update Clients");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("surtoUpdate");
    }

}

