import controller.Controller;
import model.Parser;
import view.MainView;

import javax.swing.*;
import java.io.IOException;

public class ServerMain {
    public static void main(String args[]) {
        Parser parser = new Parser();
        try {
            parser.readJsonFile();
            MainView mainView = new MainView();
            Controller controller = new Controller(mainView,parser);
            mainView.registerController(controller);
            mainView.setVisible(true);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Fichero Json incorrecto","Error",JOptionPane.WARNING_MESSAGE);
        }

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

        /*
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // creem la view

                // creem el model
                ServerGrid model = new ServerGrid();

                // en aquest cas no usem controlador
                // creem el servidor i establim relacions
                GameController c = new GameController(model);
                Server server = new Server(c, model);
                server.startService();
                // fem la view visible

                // iniciem el servidor

            }
        });*/

    }
}
