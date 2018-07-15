package model;

import java.io.Serializable;

public class Mode extends Total implements Serializable{
    private static final long serialVersionUID = 3L;

    private int mode;

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
