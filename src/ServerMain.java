

import controller.GameController;
import model.ServerGrid;
import network.Server;

import javax.swing.*;

public class ServerMain {
    public static void main(String args[]) {
       /* System.out.println("estic al server");
        LocalDate localDate = LocalDate.now();
        Parser parser = new Parser();
        try {
           // parser.readJsonFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConectorDB conn = new ConectorDB(parser.getUser(), parser.getPassword(), parser.getDatabase(), parser.getPort(), parser.getDirectionIP());
        boolean connected = conn.connect();
        GestorDB gestorDB = new GestorDB(conn);
        if (connected) {
            //JUEGO DE PRUEBAS DE REGISTRO DE USUARIOS
            User usuari = new User("davidsega98@gmail.com", "1234", "davidsega98@gmail.com", localDate.toString(), localDate.toString());
            if (gestorDB.registraUsuari(usuari)) {
                System.out.println("registra");
            } else {
                System.out.println("no registra");
            }
            conn.disconnect();
        }*/


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // creem la vista

                // creem el model
                ServerGrid model = new ServerGrid();

                // en aquest cas no usem controlador
                // creem el servidor i establim relacions
                GameController c = new GameController(model);
                Server server = new Server(c, model);
                server.startService();
                // fem la vista visible

                // iniciem el servidor

            }
        });

    }
}
