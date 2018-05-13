package controller;




import view.MainView;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class WindowController implements ComponentListener {
    private MainView mainView;
    private Controller controller;

    public WindowController(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        System.out.println("HOLA");
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

}

