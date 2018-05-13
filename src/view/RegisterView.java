
package view;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class RegisterView extends JPanel {

    private JButton jbBack;
    private JButton jbMute;
    private JButton jbSignin;
    private Image icon;
    private Image muteIcon;
    private Image muteImg;
    private Image img;
    private JLabel jlNickname;
    private JLabel jlEmail;
    private JLabel jlPassword;
    private JLabel jlRepeatPassword;
    private JTextField jtfNickname;
    private JTextField jtfEmail;
    private JPasswordField jtfPassword;
    private JPasswordField jtfRepeatPassword;
    private Font font;



    public RegisterView(){
        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        createButtons();
        createLabels();
        addButtons();



    }

    public void registerControllerButton(ActionListener controller){
        jbBack.addActionListener(controller);
        jbBack.setActionCommand("Return");
        jbMute.addActionListener(controller);
        jbMute.setActionCommand("Mute");
        jtfNickname.addActionListener(controller);
        jtfPassword.addActionListener(controller);
        jtfEmail.addActionListener(controller);
        jtfRepeatPassword.addActionListener(controller);
        jbSignin.addActionListener(controller);
        jbSignin.setActionCommand("Signin");
    }

    public void registerControllerMouse(MouseListener controller){
        jbBack.addMouseListener(controller);
        jbMute.addMouseListener(controller);
        jtfRepeatPassword.addMouseListener(controller);
        jtfEmail.addMouseListener(controller);
        jtfPassword.addMouseListener(controller);
        jtfNickname.addMouseListener(controller);
        jbSignin.addMouseListener(controller);
    }

    public void augmentButtons(String button, int x){
        if(button.equals("Mute")){
            muteIcon = muteImg.getScaledInstance(x,x,Image.SCALE_DEFAULT);
            jbMute.setIcon(new ImageIcon(muteIcon));
        }
        if(button.equals("Return")){
            icon = img.getScaledInstance(x,x,Image.SCALE_DEFAULT);
            jbBack.setIcon(new ImageIcon(icon));

        }

    }


    class CustomeBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y,
                                int width, int height) {
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(12));
            GradientPaint paint = new GradientPaint( x, y, new Color(157, 207, 222), x , y +height, Color.WHITE, false );
            g2d.setPaint(paint);
            g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
        }
    }


    public void changeTextField(String name){
        if(name.equals("Nickname")){
            jtfNickname.setText("");
            jtfNickname.setForeground(Color.black);
        }else if(name.equals("Password1")){
            jtfPassword.setText("");
            jtfPassword.setForeground(Color.black);
        }else if(name.equals("Email")){
            jtfEmail.setText("");
            jtfEmail.setForeground(Color.black);
        }else if(name.equals("RPassword")){
            jtfRepeatPassword.setText("");
            jtfRepeatPassword.setForeground(Color.black);
        }
    }

    public void changeTextFieldEmpty(String name){
        if(name.equals("Nickname")){
            jtfNickname.setText("Please input User Name");
            jtfNickname.setForeground(Color.LIGHT_GRAY);
        }else if(name.equals("Password1")){
            jtfPassword.setText("Minimum 8 carachters");
            jtfPassword.setForeground(Color.lightGray);
        }else if(name.equals("Email")){
            jtfEmail.setText("example@gmail.com");
            jtfEmail.setForeground(Color.LIGHT_GRAY);
        }else if(name.equals("RPassword")){
            jtfRepeatPassword.setText("Minimum 8 carachters");
            jtfRepeatPassword.setForeground(Color.lightGray);
        }
    }


    public String getNickname(){
        return jtfNickname.getText();
    }
    public String getPassword(){
        String password = new String(jtfPassword.getPassword());
        return password;
    }

    public String getEmail(){
        return jtfEmail.getText();
    }
    public String getRepeatPassword(){
        String password = new String(jtfRepeatPassword.getPassword());
        return password;
    }

    public void createButtons(){
        jbBack = new JButton();
        jbMute = new JButton();

        try {
            img = new ImageIcon("data/display/neon.png").getImage();
            icon = img.getScaledInstance(80,80,Image.SCALE_DEFAULT);
            jbBack.setIcon(new ImageIcon(icon));
            jbBack.setOpaque(false);
            jbBack.setContentAreaFilled(false);
            jbBack.setBorderPainted(false);
            jbBack.setFocusPainted(false);
            jbBack.setFocusPainted(false);
            jbBack.setSize(new Dimension(40,40));

            font = Font.createFont(Font.TRUETYPE_FONT, new File("data/font2.ttf"));
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(font);
            font = font.deriveFont(25f);

            jbSignin = new JButton("SIGN IN");
            jbSignin.setFont(font);
            jbSignin.setContentAreaFilled(false);
            jbSignin.setFocusPainted(false);
            jbSignin.setBorder(BorderFactory.createEmptyBorder());
            jbSignin.setOpaque(false);//enable this to create a button border
            jbSignin.setForeground(new Color(157, 207, 222));

            muteImg = new ImageIcon("data/display/neonmute.png").getImage();
            muteIcon = muteImg.getScaledInstance(80,80,Image.SCALE_DEFAULT);
            jbMute.setIcon(new ImageIcon(muteIcon));
            jbMute.setOpaque(false);
            jbMute.setContentAreaFilled(false);
            jbMute.setBorderPainted(false);
            jbMute.setFocusPainted(false);
            jbMute.setFocusPainted(false);
            jbMute.setSize(new Dimension(40,40));
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void createLabels(){
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("data/font2.ttf"));
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(font);
            font = font.deriveFont(20f);

        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jtfPassword = new JPasswordField("Minimum 8 carachters");
        jtfRepeatPassword = new JPasswordField("Minimum 8 carachters");
        jtfNickname = new JTextField("Please input User Name");
        jtfEmail = new JTextField("example@gmail.com");


        jtfNickname.setFont(jtfNickname.getFont().deriveFont(Font.PLAIN, 15f));
        jtfPassword.setFont(jtfPassword.getFont().deriveFont(Font.PLAIN, 15f));
        jtfRepeatPassword.setFont(jtfRepeatPassword.getFont().deriveFont(Font.PLAIN, 15f));
        jtfEmail.setFont((jtfEmail.getFont().deriveFont(Font.PLAIN, 15f)));

        jtfNickname.setBorder(BorderFactory.createCompoundBorder(
                new RegisterView.CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));
        jtfPassword.setForeground(Color.gray.brighter());
        jtfNickname.setForeground(Color.gray.brighter());
        jtfEmail.setForeground(Color.gray.brighter());
        jtfRepeatPassword.setForeground(Color.gray.brighter());
        jtfEmail.setBorder(BorderFactory.createCompoundBorder(
                new RegisterView.CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));
        jtfRepeatPassword.setBorder(BorderFactory.createCompoundBorder(
                new RegisterView.CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));
        jtfPassword.setBorder(BorderFactory.createCompoundBorder(
                new RegisterView.CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));

        jlEmail = new JLabel("EMAIL: ");
        jlNickname = new JLabel("NICKNAME: ");
        jlPassword = new JLabel("PASSWORD: ");
        jlRepeatPassword = new JLabel("REPEAT PASSWORD: ");

        jlEmail.setFont(font);
        jlNickname.setFont(font);
        jlPassword.setFont(font);
        jlRepeatPassword.setFont(font);

        jlPassword.setForeground(new Color(157, 207, 222));
        jlNickname.setForeground(new Color(157, 207, 222));
        jlEmail.setForeground(new Color(157, 207, 222));
        jlRepeatPassword.setForeground(new Color(157, 207, 222));



    }

    public void addButtons(){
        JPanel jpanelAux = new JPanel();
        JPanel jpanelAux3 = new JPanel();
        jpanelAux3.setLayout(new GridLayout(2,1));

        jpanelAux.setLayout(new BorderLayout());
        jpanelAux.setOpaque(false);
        jpanelAux.add(jbBack, BorderLayout.LINE_START);
        jpanelAux.add(jbMute, BorderLayout.LINE_END);
        jpanelAux.setPreferredSize(new Dimension(100,100));

        JPanel jpLabels = new JPanel();
        jpLabels.setLayout(new GridLayout(9,2, 10,5));
        jpLabels.setOpaque(false);
        jpLabels.add(jlNickname);
        jpLabels.add(jtfNickname);

        jpLabels.add(jlEmail);
        jpLabels.add(jtfEmail);

        jpLabels.add(jlPassword);
        jpLabels.add(jtfPassword);

        jpLabels.add(jlRepeatPassword);
        jpLabels.add(jtfRepeatPassword);

        JPanel jpEmpty3 = new JPanel();
        jpEmpty3.setPreferredSize(new Dimension(100,1));
        jpLabels.add(jpEmpty3);


        JPanel jpEmpty = new JPanel();
        JPanel jpEmpty1 = new JPanel();
        JPanel jpEmpty2 = new JPanel();
        JPanel jpEmpty4 = new JPanel();


        jpEmpty.setPreferredSize(new Dimension(250,50));
        jpEmpty1.setPreferredSize(new Dimension(250,50));
        jpEmpty2.setPreferredSize(new Dimension(100,30));

        jpEmpty.setOpaque(false);
        jpEmpty2.setOpaque(false);
        jpEmpty1.setOpaque(false);
        jpEmpty3.setOpaque(false);
        jpEmpty4.setOpaque(false);

        jpEmpty4.setLayout(new GridLayout(2,1));
        jpEmpty4.add(jbSignin);
        jpEmpty4.add(jpEmpty2);

        this.add(jpEmpty,BorderLayout.LINE_START);
        this.add(jpEmpty1,BorderLayout.LINE_END);
        this.add(jpEmpty4,BorderLayout.SOUTH);


        this.add(jpLabels, BorderLayout.CENTER);
        this.add(jpanelAux, BorderLayout.NORTH);

    }


}
