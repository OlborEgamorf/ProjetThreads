//package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Interface extends JPanel {
    
    private final int pixel = 3;
    private int longueur;
    private int largeur;
    private int mer;
    private Rectangle poste;

    private Vendeur vendeur = null;
    private Personne[] threads;
    private double zoom = 1;

    private ArrayList<Vague> vagues;

    public Interface (Plage plage){
        setVisible(true);
        JFrame frame = new JFrame("Plage");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        mer = plage.getMer();
        longueur = plage.getLongueur();
        largeur = plage.getLargeur();
        poste = plage.getPoste();
        double ratioBase = (double) (longueur+mer)/(double) largeur;
        if (largeur>dimension.getWidth() || longueur+mer>dimension.getHeight()-10){
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
            int b= (int) dimension.getWidth()-50;
            if (ratioBase>=1){
                ratioBase = (double) (largeur)/(double) (longueur+mer);
                a=longueur+mer;
                b= (int) dimension.getHeight()-50;
            }
            if (a*10<b && (longueur+mer)*10 < dimension.getHeight()-50)
                zoom = 10;
            if (a*7<b && (longueur+mer)*7 < dimension.getHeight()-50)
                zoom = 7;
            else if (a*4<b && (longueur+mer)*4 < dimension.getHeight()-50)
                zoom = 4;
            else if (a*2<b && (longueur+mer)*2 < dimension.getHeight()-50)
                zoom = 2;
            else
                zoom = 1;

            frame.setSize((int) (largeur*zoom)+14, (int) ((longueur+mer)*zoom+40));
        }

        frame.setVisible(true);
        frame.setResizable(false);
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);

        threads = plage.getThreads();
        vagues = plage.getVagues();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.decode("#FFE333"));
        g.fillRect(0, 0,(int) (largeur*zoom),(int) (longueur*zoom));
        g.setColor(Color.decode("#34a8eb"));
        g.fillRect(0,(int) (longueur*zoom), (int) (largeur*zoom), (int) (mer*zoom)+3);

        g.setColor(Color.red);
        g.fillRect((int) (poste.getD().getX()*zoom), (int) (poste.getD().getY()*zoom), (int)((poste.getB().getX()-poste.getA().getX())*zoom), (int)((poste.getA().getY()-poste.getD().getY())*zoom));

        for (Vague vague : vagues){
            Color newWhite = new Color(Color.white.getRed(), Color.white.getGreen(), Color.white.getBlue(), 127);
            g.setColor(newWhite);
            for (int i=0; i<largeur*zoom; i++){
                g.fillRect(i, (int) (vague.getPositionY()*zoom), 1, vague.getLongueur());
            }
        }

        for (Personne personne : threads) {
            if (personne.getAlive()) {
                if (personne instanceof Sauveteur){
                    g.setColor(Color.red);
                    g.fillOval((int) (personne.getPosition().getY()*zoom),(int) (personne.getPosition().getX()*zoom), pixel, pixel);
                } else if(personne instanceof Vendeur){
                    g.setColor(Color.green);
                    g.fillOval((int) (personne.getPosition().getY()*zoom),(int) (personne.getPosition().getX()*zoom), pixel, pixel);
                    g.setColor(Color.black);
                    g.fillRect((int) (personne.getPosition().getY()*zoom)+3, (int) (personne.getPosition().getX()*zoom), 2, 3);
                    g.fillRect((int) (personne.getPosition().getY()*zoom)+4, (int) (personne.getPosition().getX()*zoom), 2, 3);
                    g.fillRect((int) (personne.getPosition().getY()*zoom)+5, (int) (personne.getPosition().getX()*zoom), 2, 3);
                }
                else{
                    if (personne.getEtat() == Etat.NOYADE){
                        g.setColor(Color.white);
                        g.fillOval((int) (personne.getPosition().getY()*zoom), (int) (personne.getPosition().getX()*zoom), pixel, pixel);
                    }
                    else{
                        g.setColor(Color.gray);
                        g.fillOval((int) (personne.getPosition().getY()*zoom), (int) (personne.getPosition().getX()*zoom), pixel, pixel);
                    }

                    if (personne.isPlaced()) {
                        g.setColor(Color.black);
                        g.fillRect((int) (personne.getPositionPlage().getD().getY()*zoom), (int) (personne.getPositionPlage().getD().getX()*zoom), 2, 3);
                    }
                }
            }
        }    
        
        for (Rectangle place : Plage.placements) {
            g.setColor(Color.red);
            g.fillRect((int) (place.getD().getX()*zoom), (int) (place.getD().getY()*zoom), (int)((place.getB().getX()-place.getA().getX())*zoom), (int)((place.getA().getY()-place.getD().getY())*zoom));
        }
    }

    public void turn(){
        this.repaint();
    }
}
