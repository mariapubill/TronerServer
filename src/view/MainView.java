package view;


import controller.Controller;
import controller.WindowController;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private CardLayout layout;
    private JPanel jPanel;
    private ConfigView configView;
    private MenuView menuView;
    private GraphicView graphicView;
    private RegisterView registerView;
    public MainView(){
        this.setResizable(false);
        this.setFocusable(true);
        this.setTitle("Servidor");
        //Basic settings
        this.setMinimumSize(new Dimension(800,600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        registerView = new RegisterView();
        layout = new CardLayout();
        menuView = new MenuView();
        configView = new ConfigView();
        graphicView = new GraphicView();
        jPanel = new JPanel();
        this.setContentPane(jPanel);
        jPanel.setLayout(layout);
        jPanel.add("1",menuView);
        jPanel.add("2",configView);
        jPanel.add("3", graphicView);
        jPanel.add("4",registerView);


        layout.show(jPanel,"1");

        // this.setContentPane(jPanel);
    }

    public boolean showDialog(String message,int i) {

        if (i == 2) {
            JOptionPane jOptionPane = new JOptionPane();
            jOptionPane.showMessageDialog(null,
                    "Direccion introducida incorrecta",
                    "Error de conexion de servidor",
                    JOptionPane.ERROR_MESSAGE);
           /*
            Component[] component = jOptionPane.getComponents();
                for(Component c : component){
                    c.requestFocusInWindow();
            }*/
            // ((JOptionPane)JOptionPane.get.getTopLevelAncestor().requestFocus();

            return false;
        }   if(i ==1) {
            int reply = JOptionPane.showConfirmDialog(null, message, "Exit Game", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION && i == 1) {
                //No se si aqui habria que enviarle un mensaje al servidor
                return true;
            }
        }
        if(i == 4){
            System.out.println("El dinero a mi me llueve");
            JOptionPane jOptionPane = new JOptionPane();
            jOptionPane.showMessageDialog(null,
                    "La connexio al port ha estat establerta",
                    "Connexió feta",
                    JOptionPane.INFORMATION_MESSAGE);

        }
        if(i == 3){
            JOptionPane jOptionPane = new JOptionPane();
            jOptionPane.showMessageDialog(null,
                    message,
                    "Alert",
                    JOptionPane.INFORMATION_MESSAGE);

        }
        if(i == 10){
            JOptionPane.showMessageDialog(null,message,"Alert",JOptionPane.WARNING_MESSAGE);
        }

        return false;
    }

    public void changePanel(String which) {
        layout.show(this.getContentPane(), which);
    }


    public void registerController(Controller controller) {
        graphicView.addFocusListener(controller);
        configView.registerConfigController(controller);
        menuView.registerControllerMenu(controller);
        graphicView.registerControllerGraphic(controller);
        this.addComponentListener(new WindowController(this));
        this.addKeyListener(controller);
    }

    public ConfigView getConfigView() {
        return configView;
    }

    public void setConfigView(ConfigView configView) {
        this.configView = configView;
    }

    public MenuView getMenuView() {
        return menuView;
    }

    public void setMenuView(MenuView menuView) {
        this.menuView = menuView;
    }

    public GraphicView getGraphicView() {
        return graphicView;
    }

    public void setGraphicView(GraphicView graphicView) {
        this.graphicView = graphicView;
    }
}
