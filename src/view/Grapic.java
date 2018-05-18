package view;

import model.Score;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;



public class Grapic extends JPanel {
    private LinkedList<Score> score;
    private int mode = 1;
    private int [] valorsX;
    private int [] valorsY;
    private int espaiament;

    public Grapic(LinkedList<Score> score, int i) {
        this.mode = i;
        this.score = score;

    }

    public LinkedList<Score> getScore() {
        return score;
    }

    public void setScore(LinkedList<Score> score) {
        this.score = score;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    public void paint (Graphics g){
        Font font = new Font(getFont().getFontName(),Font.BOLD,14);
        g.setFont(font);
        System.out.println("estic dintre");
        System.out.println(this.mode + "------>mode");

        //g.fillRect(1000,600,600,500);
        if (mode == 0) {
            System.out.println("estic dintre2");
            g.drawLine(100, 480, 900, 480);
            g.drawString("Points", 50, 30);
            g.drawLine(100, 480, 100, 30);
            g.drawString("Partida", 800, 500);
            g.drawString("0", 60, 480);
        }else{
            System.out.println("estic dintre3");
            g.drawLine(100, 480, 900, 480);
            g.drawString("Points", 50, 30);
            g.drawLine(100, 480, 100, 30);
            g.drawString("Partida", 870, 500);
            g.drawString("0", 60, 480);
            int x = 0;
            int partida = 0;
            //puntuation = new Point [score.size()];
            valorsY = new int[score.size()];

            for(int i = 0; i< score.size();i++) {

                switch (this.mode){
                    case 1:
                        if(score.get(i).getType().equals("2xGame")) {

                            valorsY [partida] = (int) score.get(i).getPoints();
                            partida++;

                            /*g.drawString(String.valueOf(partida), x+100, 500);
                            g.drawString(String.valueOf(score.get(i).getPoints()), 50, y);
                            System.out.println(puntuation [i] );*/


                        }
                        break;
                    case 2:
                        if(score.get(i).getType().equals("4xGame")) {
                            partida++;
                            /*int y = (int) ((Integer) 430/score.get(i).getPoints());
                            x = x + (600/ score.size());
                            puntuation [i] = new Point(x, y);
                            g.drawString(String.valueOf(score.get(i).getPoints()), 50, y);

                            g.drawString(String.valueOf(partida), x+100, 500);
                            System.out.println(puntuation [i] );*/

                        }

                        break;
                    case 3:
                        if(score.get(i).getType().equals("Tournament")){

                            partida++;
                            /*int y = (int) ((Integer) 430/score.get(i).getPoints());
                            x = x + (600/ score.size());
                            puntuation [i] = new Point(x, y);
                            g.drawString(String.valueOf(score.get(i).getPoints()), 50, y);

                            g.drawString(String.valueOf(partida), x+100, 500);
                            System.out.println(puntuation [i] );
                            partida++;*/

                        }
                        break;
                }


            }
            if(partida !=0) {
                espaiament = calcularEspaiament(valorsY);
                paintGraphic(partida, g, espaiament, valorsY);
            }

        }

    }

    private void paintGraphic(int partida, Graphics g, int espaiament, int[] valorsY) {
        if(partida !=0) {
            int num_partida= 1;
            int X= 100+700/partida;
            g.drawLine(100, 480, X, 480-valorsY[0]*espaiament);
            g.drawString(String.valueOf(valorsY[0]), 60, 480-valorsY[0]*espaiament);
            g.drawString(String.valueOf(num_partida++), X, 500);
            for (int i = 1; i < partida; i += 2) {
                int z = i - 1;
                g.drawLine(X, 480-valorsY[z]*espaiament, X+=700/partida,  480-valorsY[i]*espaiament);
                g.drawString(String.valueOf(valorsY[i]), 60, 480-valorsY[i]*espaiament);
                g.drawString(String.valueOf(num_partida++), X, 500);

            }

        }


    }

    private int calcularEspaiament(int[] valorsY) {
        int bigger = valorsY[0];

        for (int i = 1; i<valorsY.length; i++ ){
            if(valorsY[i]> bigger){
                bigger = valorsY[i];
            }

        }
        return 400/bigger;
    }
}



