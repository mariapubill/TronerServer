package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class GraphicView extends JPanel{
    //declaració constants
    private static final String TITLE = "Statics";

    //declaració variables
    private JComboBox jcbUsers;
    private JComboBox jcbMode;


    public GraphicView(){


        this.setLayout(new BorderLayout());

        JPanel jpDi = new JPanel();
        jpDi.setLayout(new GridLayout(3,3));


        JPanel jpFlowLayout = new JPanel();
        jpFlowLayout.setLayout(new FlowLayout());
        JPanel jpFlowLayout1 = new JPanel();
        jpFlowLayout1.setLayout(new FlowLayout());

        JPanel jpGrid = new JPanel();
        jpGrid.setLayout(new GridLayout(1,2));

        //Creació JLabels
        JLabel jlUsers = new JLabel("Users:   ");
        JLabel jlMode = new JLabel("Mode:   ");

        //Creació comboBox (USER i MODE)

        jcbUsers = new JComboBox();
        jcbUsers.addItem("uno");
        jcbUsers.addItem("dosooooooooooooooo");
        jcbUsers.addItem("tres");

        jcbMode = new JComboBox();
        jcbMode.addItem("uno");
        jcbMode.addItem("dosooooooooooooooo");
        jcbMode.addItem("tres");

        jpFlowLayout.add(jlUsers);
        jpFlowLayout.add(jcbUsers);
        jpGrid.add(jpFlowLayout);

        jpFlowLayout1.add(jlMode);
        jpFlowLayout1.add(jcbMode);
        jpGrid.add(jpFlowLayout1);
        jpDi.add(jpGrid);
        //jpShowStatics.add(jpDi);
        //frame.getContentPane().add(jpShowStatics, BorderLayout.CENTER);

        //Gràfica
        Grapic gra = new Grapic();
        //jpShowStatics.add(gra, BorderLayout.SOUTH);
        //frame.add(gra);
        jpDi.add(gra);
        this.add(jpDi);

        /*Grapic g = new Grapic();
        JFrame frame = new JFrame();
        frame.add(g);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550,400);
        //frame.setBounds(20,20, 500,500);
        frame.setVisible(true);*/





    }


    public void registerControllerGraphic(Controller controller) {
        jcbMode.addActionListener(controller);
        jcbUsers.addActionListener(controller);
    }

    public JComboBox getJcbUsers() {
        return jcbUsers;
    }

    public void setJcbUsers(JComboBox jcbUsers) {
        this.jcbUsers = jcbUsers;
    }

    public JComboBox getJcbMode() {
        return jcbMode;
    }

    public void setJcbMode(JComboBox jcbMode) {
        this.jcbMode = jcbMode;
    }
}
