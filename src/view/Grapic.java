package view;

import javax.swing.*;
import java.awt.*;

public class Grapic extends JPanel {
        @Override
        public void paint(Graphics g) {
            g.setColor (Color.black.darker());
            g.drawRoundRect(25, 0, 480, 175, 25, 25);

            g.setColor (Color.black);
            g.drawLine(20, 150, 450,150);//eix x
            g.drawLine(60,20, 60, 160);//eix y
            g.drawString("[Match]", 460, 153);
            g.drawString("[Points]", 40, 15);
        }
    }



