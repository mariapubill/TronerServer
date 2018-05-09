package controller;



import model.Petition;
import model.ServerGrid;
import network.DedicatedServer;

import java.awt.*;
import java.awt.event.KeyEvent;


public class AddMovementThread extends Thread {

    private GameController c;
    private Petition newGrid;
    private DedicatedServer server;
    private boolean     go;
    private int x,y;
    private ServerGrid sg;
    private int color;

    public AddMovementThread(GameController c, DedicatedServer server, ServerGrid model, Point posicions) {
        this.c = c;
        this.go = false;
        this.server = server;
        this.sg = model;
        x= (int) posicions.getX();
        y= (int) posicions.getY();
    }


    public void setGo(boolean go) {
        this.go = go;
    }

    public void setNewGrid(Petition newGrid, int color) {
        this.newGrid = newGrid;
        this.color = color;
    }

    public void run() {
        go = true;
        System.out.println("newThread");
        while (go) {
            try {
                Thread.sleep(100);
                if (newGrid.getKey() == KeyEvent.VK_LEFT){
                    if ((y == 0) || checkPaint(x, y)){
                        System.out.println("GameOver11111111111");
                        go = false;
                        server.stopThreads();


                    } else {
                        char [][] aux=  sg.getServerGrid();
                        aux[x][y--]= (char) color;


                        sg.setServerGrid(aux);
                    }


                }
                if (newGrid.getKey() == KeyEvent.VK_RIGHT) {
                    if((y == 119) || checkPaint(x, y)){
                        System.out.println("GameOver11111");
                        server.stopThreads();

                    }else {
                        char [][] aux=  sg.getServerGrid();
                        aux[x][y++]= (char) color;


                        sg.setServerGrid(aux);

                    }

                }
                if (newGrid.getKey() == KeyEvent.VK_UP){

                    if((x == 0) || checkPaint(x, y)){
                        System.out.println("GameOver11111111");
                        server.stopThreads();
                        go = false;

                    }else {
                        char [][] aux=  sg.getServerGrid();
                        aux[x--][y]= (char) color;



                        sg.setServerGrid(aux);
                    }
                }
                if (newGrid.getKey() == KeyEvent.VK_DOWN){

                    if((x == 119) || checkPaint(x, y)){
                        System.out.println("GameOver11111111111");
                        go = false;
                        server.stopThreads();
                    }else {
                        char [][] aux=  sg.getServerGrid();
                        aux[x++][y]= (char) color;
                        sg.setServerGrid(aux);
                    }

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private boolean checkPaint(int x, int y) {
        char [][] aux=  sg.getServerGrid();

        if (aux[x][y] == 0 ) {
            return false;
        }else {
            return true;
        }
    }
}
