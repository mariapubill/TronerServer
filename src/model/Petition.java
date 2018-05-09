package model;

import java.io.Serializable;

public class Petition implements Serializable{

    private int key;
    private static final long serialVersionUID = 5940092344965987487L;

    public Petition( int key) {
        this.key = key;

    }

    public void setKey(int key) {
        this.key = key;
    }



    public int getKey() {
        return key;
    }
}