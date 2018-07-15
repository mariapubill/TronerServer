package model;

import java.awt.*;
import java.io.Serializable;

//al dedicatedServerser li dic model
/**
 * creem la classe que contindrà la informació que el servidor envia al client
 * servidor --> client
 */
public class ServerGrid implements Serializable {
    private char[][] serverGrid;
    private Point[] posicions;
    private static final long serialVersionUID = 5940092344965987887L;

    public ServerGrid() {
        this.serverGrid = new char[120][120];
        this.posicions = new Point[4];
        initPosicions();


    }

    public char[][] getServerGrid() {
        return serverGrid;
    }

    public void setServerGrid(char[][] serverGrid) {
        this.serverGrid = serverGrid;
    }


    private void initPosicions(){
        posicions [0] = new Point(100,30);
        posicions [1] = new Point(20,30);
        posicions [2] = new Point(100,90);
        posicions [3] = new Point(20,90);

    }

    public Point getPosicions(int i) {
        return posicions[i];
    }
}








