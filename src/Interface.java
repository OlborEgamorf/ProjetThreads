//package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Interface extends JPanel {



       /*

        // Parametre Scrollbar
        final java.awt.Scrollbar population = new java.awt.Scrollbar(Adjustable.HORIZONTAL, 0, 0, 0, 10);
        // Position Scrollbar ///////
        population.setBounds(50, 0, 200, 50);
        f.add(population);
        f.add(label);
        // Taille de la fenetre
        f.setSize(300, 125);
        f.setLayout(null);
        f.setVisible(true);
        population.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                label.setText("Vitesse : "+ population.getValue());
            }
        });
    }

        */
    private final int pixel = 4;
    private int longueur;
    private int largeur;
    private int mer;
    private Personne[] threads;
    private Case[][] matrice;
    private double[] ratio = new double[2];

    public Interface(Plage plage){
        setVisible(true);
        JFrame frame = new JFrame("Plage");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        mer = plage.getMer();
        longueur = plage.getLongueur();
        largeur = plage.getLargeur();
        double ratioBase = (double) (longueur+mer)/(double) largeur;
        if (ratioBase<1){
            frame.setSize((int) dimension.getWidth(), (int) ((int) dimension.getHeight()*ratioBase));
            ratio[0] = (dimension.getHeight()/(longueur+mer)*ratioBase);
            ratio[1] = dimension.getWidth()/largeur;
        }
        else {
            ratioBase = (double) (largeur)/(double) (longueur+mer);
            frame.setSize((int) ((int) dimension.getWidth()*ratioBase), (int) dimension.getHeight());
            ratio[0] = (dimension.getHeight()/(longueur+mer));
            ratio[1] = (dimension.getWidth()/largeur)*ratioBase;
        }

        frame.setVisible(true);
        frame.setResizable(false);
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);

        threads = plage.getThreads();
        matrice = plage.getMatrice();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.decode("#FFE333"));
        g.fillRect(0, 0,(int) (largeur*ratio[1]),(int) (longueur*ratio[0]));
        g.setColor(Color.decode("#338AFF"));
        g.fillRect(0,(int) (longueur*ratio[0]), (int) (largeur*ratio[1]), (int) (mer*ratio[0]));

        for (Personne personne : threads) {
            if (personne.getAlive()) {
                if (personne.getEstSauveteur()){
                    g.setColor(Color.red);
                    g.fillOval((int) (personne.getPosition()[1]*ratio[1]),(int) (personne.getPosition()[0]*ratio[0]), pixel, pixel);
                }
                else{
                    if (personne.getEtat() == Etat.NOYADE){
                        g.setColor(Color.white);
                        g.fillOval((int) (personne.getPosition()[1]*ratio[1]), (int) (personne.getPosition()[0]*ratio[0]), pixel, pixel);
                    }
                    else{
                        g.setColor(Color.gray);
                        g.fillOval((int) (personne.getPosition()[1]*ratio[1]), (int) (personne.getPosition()[0]*ratio[0]), pixel, pixel);
                        
                    }
                    g.setColor(Color.black);
                    /*if (personne.getPositionPlage() != null) {
                        g.fillRect(personne.getPositionPlage()[1], personne.getPositionPlage()[0], pixel, pixel);
                    }*/
                }
                /*if (personne.isPlace()) {
                    int[] positionPlage = personne.getPositionPlage();
                    g.setColor(Color.black);
                    g.fillRect(positionPlage[0], positionPlage[1], pixel, pixel);
                    g.fillRect(positionPlage[0], positionPlage[1]-1, pixel, pixel);
                    g.fillRect(positionPlage[0]-1, positionPlage[1], pixel, pixel);
                    g.fillRect(positionPlage[0]-1, positionPlage[1]-1, pixel, pixel);
                    g.fillRect(positionPlage[0]-2, positionPlage[1], pixel, pixel);
                    g.fillRect(positionPlage[0]-2, positionPlage[1]-1, pixel, pixel);
                }*/
            }
        }
        /*for (int i = 0; i < longueur; i++){
            for (int j = 0; j < largeur; j++){
                if (matrice [i][j].getType() == Type.TEMPORAIRE){
                    g.setColor(Color.red);
                    g.fillRect((int) (j*ratio[1]), (int) (i*ratio[0]), pixel, pixel);
                    g.fillRect((int) (j*ratio[1]+1), (int) (i*ratio[0]), pixel, pixel);
                    g.fillRect((int) (j*ratio[1]+2), (int) (i*ratio[0]), pixel, pixel);
                    g.fillRect((int) (j*ratio[1]), (int) (i*ratio[0]+1), pixel, pixel);
                    g.fillRect((int) (j*ratio[1]+1), (int) (i*ratio[0]+1), pixel, pixel);
                    g.fillRect((int) (j*ratio[1]+2), (int) (i*ratio[0]+1), pixel, pixel);
                    j+=2;
                }
            }
        }*/
        for (int i = 0; i < longueur; i++){
            for (int j = 0; j < largeur; j++){
                if (matrice [i][j].getType() == Type.AFFAIRES){
                    g.setColor(Color.black);
                    g.fillRect((int) (j*ratio[1]), (int) (i*ratio[0]), pixel, pixel);
                    g.fillRect((int) (j*ratio[1]+1), (int) (i*ratio[0]), pixel, pixel);
                    g.fillRect((int) (j*ratio[1]+2), (int) (i*ratio[0]), pixel, pixel);
                    g.fillRect((int) (j*ratio[1]), (int) (i*ratio[0])+1, pixel, pixel);
                    g.fillRect((int) (j*ratio[1]+1), (int) (i*ratio[0])+1, pixel, pixel);
                    g.fillRect((int) (j*ratio[1]+2), (int) (i*ratio[0])+1, pixel, pixel);
                    j+=2;
                }
            }
        }
    }

    public void turn(){
        this.repaint();
    }
}
