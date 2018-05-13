package view;



import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class ConfigView extends JPanel {
    //declaració constants
    private static final String TITLE = "Configuration";
    //declaració variables
    private JPanel jpNumPort;
    private JButton jbStart;
    private JButton jbStopConnexion;
    private JTextField textField;
    private JButton jbReturnButton;
    public ConfigView(){

            /*
            setSize(1150,400);
            setTitle(TITLE);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            */
        this.setLayout(new BorderLayout());
        JPanel jpGrid = new JPanel();
        //jpGrid.setLayout(new GridLayout(3,1));
        jpGrid.setLayout(new GridBagLayout());
        //SpringLayout --> NumPort
        SpringLayout layout = new SpringLayout();
        jpNumPort = new JPanel();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        JLabel jlEmail = new JLabel("Port Number: ");
        jlEmail.setFont(new Font("Adobe Fan Heiti Std B", Font.BOLD, 20));
        textField = new JTextField("", 15);
        panel.add(jlEmail);
        panel.add(textField);



        layout.putConstraint(SpringLayout.WEST, jlEmail,50, SpringLayout.WEST, jpNumPort);
        layout.putConstraint(SpringLayout.NORTH, jlEmail,80, SpringLayout.NORTH, jpNumPort);
        layout.putConstraint(SpringLayout.WEST, textField,10, SpringLayout.EAST, jlEmail);
        layout.putConstraint(SpringLayout.NORTH, textField,88, SpringLayout.NORTH,
                jpNumPort);

        layout.putConstraint(SpringLayout.EAST, panel,0, SpringLayout.EAST, textField);
        layout.putConstraint(SpringLayout.SOUTH, panel,0, SpringLayout.SOUTH, textField);
        jpNumPort.add(panel);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NORTH;
        c.ipady = 10;
        c.ipadx = 20;
        c.weightx = 0;
        c.gridwidth =0;
        c.gridx = 0;
        c.gridy = 2;
        jpGrid.add(jpNumPort,c);



        //JButton --> START
        JPanel jpInf = new JPanel();
        jbStart = new JButton("           START         ");
        jpInf.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.ipadx = 20;
        c.weightx = 0;
        c.gridwidth =0;
        c.gridx = 0;
        c.gridy = 2;
        jpInf.add(jbStart, c);
        JPanel jpInfButton = new JPanel();
        jpInfButton.setLayout(new GridLayout(1,2));
        jpInfButton.add(jpInf);
        jpGrid.add(jpInfButton);


        //JButton --> STOP CONNEXION
        JPanel jpInfStop = new JPanel();
        jbStopConnexion = new JButton("STOP CONNEXION");
        jpInfStop.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();
        d.fill = GridBagConstraints.HORIZONTAL;
        d.ipady = 30;
        d.ipadx = 45;
        d.weightx = 0;
        d.gridwidth =0;
        d.gridx = 30;
        d.gridy = 2;
        jpInfStop.add(jbStopConnexion, d);

        jbReturnButton = new JButton("RETURN");


        d.fill = GridBagConstraints.SOUTH;
        d.ipady = 30;
        d.ipadx = 45;
        d.weightx = 0;
        d.gridwidth =0;
        d.gridx = 30;
        d.gridy = 30;
        jpInfStop.add(jbReturnButton, d);


        jpInfButton.add(jpInfStop);
        jpGrid.add(jpInfButton);
        this.add(jpGrid,BorderLayout.CENTER);

        //------------RETURN BUTTON----------------------

        // getContentPane().add(jpGrid, BorderLayout.CENTER);
    }
    public void registerConfigController(Controller controller){
        jbStart.addActionListener(controller);
        jbStart.setActionCommand("Start");
        jbStopConnexion.addActionListener(controller);
        jbStopConnexion.setActionCommand("Stop");
        textField.setActionCommand("Label");
        textField.addFocusListener(controller);


    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }


    public void changeTextField() {
        textField.setText("");
    }
}
