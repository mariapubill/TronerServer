package controller;
import model.ServerGrid;
import network.Server;


public class GameController {
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String RIGHT = "right";
    private static final String LEFT = "left";


    private ServerGrid sg;
    private Server server;

    public GameController(ServerGrid model) {
        this.sg = model;
        //this.server = new Server(this, sg);
//        server.startService();


    }
}
