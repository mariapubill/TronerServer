package view;

import controller.Controller;
import model.Score;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class GraphicView extends JPanel{
    //declaració constants
    private static final String TITLE = "Statics";

    //declaració variables
    private JComboBox jcbUsers;
    private JComboBox jcbMode;
    private Grapic gra;
    private JButton jbExit;
    private LinkedList<Score> score;

    public GraphicView(){


        this.setLayout(new BorderLayout());

        JPanel jpDi = new JPanel();
        jpDi.setLayout(new BorderLayout());


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


        jcbMode = new JComboBox();
        jcbMode.addItem("2 Players");
        jcbMode.addItem("4 players");
        jcbMode.addItem("Tournement");

        jpFlowLayout.add(jlUsers);
        jpFlowLayout.add(jcbUsers);
        jpGrid.add(jpFlowLayout);

        jpFlowLayout1.add(jlMode);
        jpFlowLayout1.add(jcbMode);
        jpGrid.add(jpFlowLayout1);
        jpDi.add(jpGrid, BorderLayout.NORTH);

        gra = new Grapic( score, 0);

        jbExit = new JButton("Exit");

        jpDi.add(gra);
        this.add(jpDi);
        this.add(jbExit,BorderLayout.PAGE_END);






    }


    public void registerControllerGraphic(Controller controller) {
        jcbMode.addActionListener(controller);
        jcbMode.setActionCommand("Change_user_userMode");
        jcbUsers.addActionListener(controller);
        jcbUsers.setActionCommand("Change_user_Player");
        jbExit.addActionListener(controller);
        jbExit.setActionCommand("ExitGraphic");

    }

    public JComboBox getJcbUsers() {
        return jcbUsers;
    }

    public void setJcbUsers(LinkedList<User> users) {
        if(jcbUsers.getItemCount()!=0){
           //for(int i = 0; i<jcbUsers.getItemCount(); i++){
            jcbUsers.removeAllItems();
           //}

        }

            for (int i = 0; i < users.size(); i++) this.jcbUsers.addItem(users.get(i).getNickname());


    }

    public JComboBox getJcbMode() {
        return jcbMode;
    }

    public void setJcbMode(JComboBox jcbMode) {
        this.jcbMode = jcbMode;
    }


    public void setGra(LinkedList<Score> score, int i) {
        System.out.println(i + "------>mode");
       // Grapic gra = new Grapic(score,i);
        this.gra.setMode(i);
        this.gra.setScore(score);
        System.out.println(i + "------>mode");
        gra.repaint();
    }
}
