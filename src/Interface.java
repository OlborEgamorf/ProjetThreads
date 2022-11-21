//package src;

import javax.swing.*;
import java.awt.*;

public class Interface extends JPanel {
    
    private final int pixel = 3;
    private int longueur;
    private int largeur;
    private int mer;
    private Rectangle poste;
    private Personne[] threads;
    private double zoom = 1;

    public Interface (Plage plage){
        setVisible(true);
        JFrame frame = new JFrame("Plage");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        mer = plage.getMer();
        longueur = plage.getLongueur();
        largeur = plage.getLargeur();
        poste = plage.getPoste();
        double ratioBase = (double) (longueur+mer)/(double) largeur;
        if (largeur>dimension.getWidth() || longueur+mer>dimension.getHeight()){
            boolean fini = false;
            double i= 1.25;
            if (largeur/dimension.getWidth()>longueur/dimension.getHeight()){
                while (!fini){
                    if (largeur/i<dimension.getWidth()){
                        zoom = 1/i;
                        frame.setSize((int) (largeur*zoom), (int) ((longueur+mer)*zoom+((mer*zoom) - ((mer*zoom)*ratioBase))));
                        fini= true;
                    }
                    i+=0.25;
                }
            }
            else {
                ratioBase = (double) largeur/(double) (longueur+mer);
                while (!fini){
                    if ((longueur+mer)/i<dimension.getHeight()){
                        zoom = 1/i;
                        frame.setSize((int) (largeur*zoom)+16, (int) (((longueur+mer)*zoom)+((mer*zoom)*ratioBase)));
                        fini= true;
                    }
                    i+=0.25;
                }
            }
        }
        else {
            int a= largeur;
            int b= (int) dimension.getWidth();
            if (ratioBase>1){
                ratioBase = (double) (largeur)/(double) (longueur+mer);
                a=longueur+mer;
                b= (int) dimension.getHeight();
            }
            if (a*10<b)
                zoom = 10;
            else if (a*4<b)
                zoom = 4;
            else if (a*2<b)
                zoom = 2;
            else
                zoom = 1;
            frame.setSize((int) (largeur*zoom), (int) ((longueur+mer)*zoom+(mer*ratioBase)));
        }

        frame.setVisible(true);
        frame.setResizable(false);
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);

        threads = plage.getThreads();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.decode("#FFE333"));
        g.fillRect(0, 0,(int) (largeur*zoom),(int) (longueur*zoom));
        g.setColor(Color.decode("#34a8eb"));
        g.fillRect(0,(int) (longueur*zoom), (int) (largeur*zoom), (int) (mer*zoom));

        g.setColor(Color.red);
        g.fillRect((int) (poste.getD()[1]*zoom), (int) (poste.getD()[0]*zoom), 10, 6);

        for (Personne personne : threads) {
            if (personne.getAlive()) {
                if (personne instanceof Sauveteur){
                    g.setColor(Color.red);
                    g.fillOval((int) (personne.getPosition()[1]*zoom),(int) (personne.getPosition()[0]*zoom), pixel, pixel);
                }
                else{
                    if (personne.getEtat() == Etat.NOYADE){
                        g.setColor(Color.white);
                        g.fillOval((int) (personne.getPosition()[1]*zoom), (int) (personne.getPosition()[0]*zoom), pixel, pixel);
                    }
                    else{
                        g.setColor(Color.gray);
                        g.fillOval((int) (personne.getPosition()[1]*zoom), (int) (personne.getPosition()[0]*zoom), pixel, pixel);
                    }

                    if (personne.isPlaced()) {
                        g.setColor(Color.black);
                        g.fillRect((int) (personne.getPositionPlage().getD()[1]*zoom), (int) (personne.getPositionPlage().getD()[0]*zoom), 2, 3);
                    }
                }
            }
        }        
    }

    public void turn(){
        this.repaint();
    }
}
